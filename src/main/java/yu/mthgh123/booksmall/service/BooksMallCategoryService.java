package yu.mthgh123.booksmall.service;

import yu.mthgh123.booksmall.controller.vo.BooksMallIndexCategoryVO;
import yu.mthgh123.booksmall.controller.vo.SearchPageCategoryVO;
import yu.mthgh123.booksmall.entity.GoodsCategory;
import yu.mthgh123.booksmall.util.PageQueryUtil;
import yu.mthgh123.booksmall.util.PageResult;

import java.util.List;

public interface BooksMallCategoryService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getCategorisPage(PageQueryUtil pageUtil);

    String saveCategory(GoodsCategory goodsCategory);

    String updateGoodsCategory(GoodsCategory goodsCategory);

    GoodsCategory getGoodsCategoryById(Long id);

    Boolean deleteBatch(Integer[] ids);

    /**
     * 返回分类数据(首页调用)
     *
     * @return
     */
    List<BooksMallIndexCategoryVO> getCategoriesForIndex();

    /**
     * 返回分类数据(搜索页调用)
     *
     * @param categoryId
     * @return
     */
    SearchPageCategoryVO getCategoriesForSearch(Long categoryId);

    /**
     * 根据parentId和level获取分类列表
     *
     * @param parentIds
     * @param categoryLevel
     * @return
     */
    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel);
}
