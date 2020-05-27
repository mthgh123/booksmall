package yu.mthgh123.booksmall.controller.admin;

import yu.mthgh123.booksmall.common.Constants;
import yu.mthgh123.booksmall.common.BooksMallCategoryLevelEnum;
import yu.mthgh123.booksmall.common.ServiceResultEnum;
import yu.mthgh123.booksmall.entity.GoodsCategory;
import yu.mthgh123.booksmall.entity.BooksMallGoods;
import yu.mthgh123.booksmall.service.BooksMallCategoryService;
import yu.mthgh123.booksmall.service.BooksMallGoodsService;
import yu.mthgh123.booksmall.util.PageQueryUtil;
import yu.mthgh123.booksmall.util.Result;
import yu.mthgh123.booksmall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yu
 * @link https://github.com/
 */
@Controller
@RequestMapping("/admin")
public class BooksMallGoodsController {

    @Resource
    private BooksMallGoodsService booksMallGoodsService;
    @Resource
    private BooksMallCategoryService booksMallCategoryService;

    @GetMapping("/goods")
    public String goodsPage(HttpServletRequest request) {
        request.setAttribute("path", "newbee_mall_goods");
        return "admin/newbee_mall_goods";
    }

    @GetMapping("/goods/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path", "edit");
        //查询所有的一级分类
        //因为Collections.singletonList(0L)输入的参数是0L,即parentIds中只有0，而parentId=0的分类都是一级分类，所以说是查询所有的一级分类
        List<GoodsCategory> firstLevelCategories = booksMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), BooksMallCategoryLevelEnum.LEVEL_ONE.getLevel());
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            //查询一级分类列表中第一个实体的所有二级分类
            //firstLevelCategories.get(0).getCategoryId()就是对应一级分类列表中第一个实体的categoryId，因为这个categoryId属于一级分类的一个实体，那么把这个categoryId作为parentId来进行查询的话，可以查出该categoryId下的所有二级分类，此时需要的是把categoryLevel设定为二级，如：BooksMallCategoryLevelEnum.LEVEL_TWO.getLevel()
            List<GoodsCategory> secondLevelCategories = booksMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), BooksMallCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                request.setAttribute("firstLevelCategories", firstLevelCategories);
                request.setAttribute("secondLevelCategories", secondLevelCategories);
                request.setAttribute("path", "goods-edit");
                return "admin/newbee_mall_goods_edit";
            }
        }
        return "error/error_5xx";
    }

    //修改书籍信息的控制器方法
    @GetMapping("/goods/edit/{goodsId}")
    public String edit(HttpServletRequest request, @PathVariable("goodsId") Long goodsId) {
        request.setAttribute("path", "edit");
        BooksMallGoods booksMallGoods = booksMallGoodsService.getBooksMallGoodsById(goodsId);
        if (booksMallGoods == null) {
            return "error/error_400";
        }
        if (booksMallGoods.getGoodsCategoryId() > 0) {
            if (booksMallGoods.getGoodsCategoryId() != null || booksMallGoods.getGoodsCategoryId() > 0) {
                //有分类字段则查询相关分类数据返回给前端以供分类的二级联动显示
                GoodsCategory currentGoodsCategory = booksMallCategoryService.getGoodsCategoryById(booksMallGoods.getGoodsCategoryId());
                //商品表中存储的分类id字段为二级分类的id，不为二级分类则是错误数据，下方进行判空以及判断是否为二级分类
                if (currentGoodsCategory != null && currentGoodsCategory.getCategoryLevel() == BooksMallCategoryLevelEnum.LEVEL_TWO.getLevel()) {
                    //查询所有的一级分类，和上面的("/goods/edit")控制器方法的查询代码一样
                    List<GoodsCategory> firstLevelCategories = booksMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), BooksMallCategoryLevelEnum.LEVEL_ONE.getLevel());
                    //根据parentId查询当前parentId下所有的二级分类
                    List<GoodsCategory> secondLevelCategories = booksMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(currentGoodsCategory.getParentId()), BooksMallCategoryLevelEnum.LEVEL_TWO.getLevel());
                    //查询当前二级分类的父级一级分类
                    GoodsCategory firstCategory = booksMallCategoryService.getGoodsCategoryById(currentGoodsCategory.getParentId());
                    if (firstCategory != null) {
                        //所有分类数据都得到之后放到request对象中供前端读取
                        request.setAttribute("firstLevelCategories", firstLevelCategories);
                        request.setAttribute("secondLevelCategories", secondLevelCategories);
                        request.setAttribute("firstLevelCategoryId", firstCategory.getCategoryId());
                        request.setAttribute("secondLevelCategoryId", currentGoodsCategory.getCategoryId());
                    }
                }
            }
        }
        if (booksMallGoods.getGoodsCategoryId() == 0) {
            //查询所有的一级分类
            List<GoodsCategory> firstLevelCategories = booksMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), BooksMallCategoryLevelEnum.LEVEL_ONE.getLevel());
            if (!CollectionUtils.isEmpty(firstLevelCategories)) {
                //查询一级分类列表中第一个实体的所有二级分类
                List<GoodsCategory> secondLevelCategories = booksMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), BooksMallCategoryLevelEnum.LEVEL_TWO.getLevel());
                if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                    //查询二级分类列表中第一个实体的所有三级分类
                    List<GoodsCategory> thirdLevelCategories = booksMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), BooksMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                    request.setAttribute("firstLevelCategories", firstLevelCategories);
                    request.setAttribute("secondLevelCategories", secondLevelCategories);
                    request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                }
            }
        }
        request.setAttribute("goods", booksMallGoods);
        request.setAttribute("path", "goods-edit");
        return "admin/newbee_mall_goods_edit";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/goods/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(booksMallGoodsService.getBooksMallGoodsPage(pageUtil));
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/goods/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody BooksMallGoods booksMallGoods) {
        if (StringUtils.isEmpty(booksMallGoods.getGoodsName())
                || StringUtils.isEmpty(booksMallGoods.getAuthor())
                || Objects.isNull(booksMallGoods.getOriginalPrice())
                || Objects.isNull(booksMallGoods.getGoodsCategoryId())
                || Objects.isNull(booksMallGoods.getSellingPrice())
                || Objects.isNull(booksMallGoods.getStockNum())
                || Objects.isNull(booksMallGoods.getGoodsSellStatus())
                || StringUtils.isEmpty(booksMallGoods.getGoodsCoverImg())
                || StringUtils.isEmpty(booksMallGoods.getGoodsDetailContent())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = booksMallGoodsService.saveBooksMallGoods(booksMallGoods);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }


    /**
     * 修改
     */
    @RequestMapping(value = "/goods/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody BooksMallGoods booksMallGoods) {
        if (Objects.isNull(booksMallGoods.getGoodsId())
                || StringUtils.isEmpty(booksMallGoods.getGoodsName())
                || StringUtils.isEmpty(booksMallGoods.getAuthor())
                || Objects.isNull(booksMallGoods.getOriginalPrice())
                || Objects.isNull(booksMallGoods.getSellingPrice())
                || Objects.isNull(booksMallGoods.getGoodsCategoryId())
                || Objects.isNull(booksMallGoods.getStockNum())
                || Objects.isNull(booksMallGoods.getGoodsSellStatus())
                || StringUtils.isEmpty(booksMallGoods.getGoodsCoverImg())
                || StringUtils.isEmpty(booksMallGoods.getGoodsDetailContent())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = booksMallGoodsService.updateBooksMallGoods(booksMallGoods);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 详情
     */
    @GetMapping("/goods/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Long id) {
        BooksMallGoods goods = booksMallGoodsService.getBooksMallGoodsById(id);
        if (goods == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(goods);
    }

    /**
     * 批量修改销售状态
     */
    @RequestMapping(value = "/goods/status/{sellStatus}", method = RequestMethod.PUT)
    @ResponseBody
    public Result delete(@RequestBody Long[] ids, @PathVariable("sellStatus") int sellStatus) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (sellStatus != Constants.SELL_STATUS_UP && sellStatus != Constants.SELL_STATUS_DOWN) {
            return ResultGenerator.genFailResult("状态异常！");
        }
        if (booksMallGoodsService.batchUpdateSellStatus(ids, sellStatus)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

}