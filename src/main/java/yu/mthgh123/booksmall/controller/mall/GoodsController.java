package yu.mthgh123.booksmall.controller.mall;

import yu.mthgh123.booksmall.common.Constants;
import yu.mthgh123.booksmall.common.BooksMallException;
import yu.mthgh123.booksmall.common.ServiceResultEnum;
import yu.mthgh123.booksmall.controller.vo.BooksMallGoodsDetailVO;
import yu.mthgh123.booksmall.controller.vo.SearchPageCategoryVO;
import yu.mthgh123.booksmall.entity.BooksMallGoods;
import yu.mthgh123.booksmall.service.BooksMallCategoryService;
import yu.mthgh123.booksmall.service.BooksMallGoodsService;
import yu.mthgh123.booksmall.util.BeanUtil;
import yu.mthgh123.booksmall.util.PageQueryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yu
 * @link https://github.com/mthgh123/booksmall
 */

@Controller
public class GoodsController {

    @Resource
    private BooksMallGoodsService booksMallGoodsService;
    @Resource
    private BooksMallCategoryService booksMallCategoryService;

    @GetMapping({"/search", "/search.html"})
    public String searchPage(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (StringUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        //封装分类数据
        if (params.containsKey("goodsCategoryId") && !StringUtils.isEmpty(params.get("goodsCategoryId") + "")) {
            Long categoryId = Long.valueOf(params.get("goodsCategoryId") + "");
            SearchPageCategoryVO searchPageCategoryVO = booksMallCategoryService.getCategoriesForSearch(categoryId);
            if (searchPageCategoryVO != null) {
                request.setAttribute("goodsCategoryId", categoryId);
                request.setAttribute("searchPageCategoryVO", searchPageCategoryVO);
            }
        }
        //封装参数供前端回显
        if (params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy") + "")) {
            request.setAttribute("orderBy", params.get("orderBy") + "");
        }
        String keyword = "";
        //对keyword做过滤 去掉空格
        if (params.containsKey("keyword") && !StringUtils.isEmpty((params.get("keyword") + "").trim())) {
            keyword = params.get("keyword") + "";
        }
        request.setAttribute("keyword", keyword);
        params.put("keyword", keyword);
        //封装商品数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        request.setAttribute("pageResult", booksMallGoodsService.searchBooksMallGoods(pageUtil));
        return "mall/search";
    }

    //书籍详情页控制器方法
    @GetMapping("/goods/detail/{goodsId}")
    public String detailPage(@PathVariable("goodsId") Long goodsId, HttpServletRequest request) {
        if (goodsId < 1) {
            return "error/error_5xx";
        }
        BooksMallGoods goods = booksMallGoodsService.getBooksMallGoodsById(goodsId);
        if (goods == null) {
            BooksMallException.fail(ServiceResultEnum.GOODS_NOT_EXIST.getResult());
        }
        if (Constants.SELL_STATUS_UP != goods.getGoodsSellStatus()){
            BooksMallException.fail(ServiceResultEnum.GOODS_PUT_DOWN.getResult());
        }
        BooksMallGoodsDetailVO goodsDetailVO = new BooksMallGoodsDetailVO();
        BeanUtil.copyProperties(goods, goodsDetailVO);
        goodsDetailVO.setGoodsCarouselList(goods.getGoodsCarousel().split(","));
        request.setAttribute("goodsDetail", goodsDetailVO);
        return "mall/detail";
    }
}
