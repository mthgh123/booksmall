package yu.mthgh123.booksmall.controller.mall;

import yu.mthgh123.booksmall.common.Constants;
import yu.mthgh123.booksmall.common.IndexConfigTypeEnum;
import yu.mthgh123.booksmall.controller.vo.BooksMallIndexCarouselVO;
import yu.mthgh123.booksmall.controller.vo.BooksMallIndexCategoryVO;
import yu.mthgh123.booksmall.controller.vo.BooksMallIndexConfigGoodsVO;
import yu.mthgh123.booksmall.service.BooksMallCarouselService;
import yu.mthgh123.booksmall.service.BooksMallCategoryService;
import yu.mthgh123.booksmall.service.BooksMallIndexConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private BooksMallCarouselService booksMallCarouselService;

    @Resource
    private BooksMallIndexConfigService booksMallIndexConfigService;

    @Resource
    private BooksMallCategoryService booksMallCategoryService;

    @GetMapping({"/index", "/", "/index.html"})
    public String indexPage(HttpServletRequest request) {
        //1.读取轮播图相关数据
        List<BooksMallIndexCarouselVO> carousels = booksMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
        //2.热门书籍的数据获取
        List<BooksMallIndexConfigGoodsVO> hotBooks = booksMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_BOOKS_HOT.getType(), Constants.INDEX_BOOKS_HOT_NUMBER);
        //3.新书上线数据的获取
        List<BooksMallIndexConfigGoodsVO> newBooks = booksMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_BOOKS_NEW.getType(), Constants.INDEX_BOOKS_NEW_NUMBER);
        request.setAttribute("carousels", carousels);//轮播图
        request.setAttribute("hotBooks", hotBooks);//热门书籍
        request.setAttribute("newBooks", newBooks);//新书上线
        return "mall/index";
    }
}
