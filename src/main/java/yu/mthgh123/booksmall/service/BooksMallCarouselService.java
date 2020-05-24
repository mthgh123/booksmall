package yu.mthgh123.booksmall.service;

import yu.mthgh123.booksmall.controller.vo.BooksMallIndexCarouselVO;
import yu.mthgh123.booksmall.entity.Carousel;
import yu.mthgh123.booksmall.util.PageQueryUtil;
import yu.mthgh123.booksmall.util.PageResult;

import java.util.List;

public interface BooksMallCarouselService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getCarouselPage(PageQueryUtil pageUtil);

    String saveCarousel(Carousel carousel);

    String updateCarousel(Carousel carousel);

    Carousel getCarouselById(Integer id);

    Boolean deleteBatch(Integer[] ids);

    /**
     * 返回固定数量的轮播图对象(首页调用)
     *
     * @param number
     * @return
     */
    List<BooksMallIndexCarouselVO> getCarouselsForIndex(int number);
}
