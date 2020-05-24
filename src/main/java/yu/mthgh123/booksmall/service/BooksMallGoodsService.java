package yu.mthgh123.booksmall.service;

import yu.mthgh123.booksmall.entity.BooksMallGoods;
import yu.mthgh123.booksmall.util.PageQueryUtil;
import yu.mthgh123.booksmall.util.PageResult;

import java.util.List;

public interface BooksMallGoodsService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getBooksMallGoodsPage(PageQueryUtil pageUtil);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    String saveBooksMallGoods(BooksMallGoods goods);

    /**
     * 批量新增商品数据
     *
     * @param newBeeMallGoodsList
     * @return
     */
    void batchSaveBooksMallGoods(List<BooksMallGoods> newBeeMallGoodsList);

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    String updateBooksMallGoods(BooksMallGoods goods);

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    BooksMallGoods getBooksMallGoodsById(Long id);

    /**
     * 批量修改销售状态(上架下架)
     *
     * @param ids
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] ids, int sellStatus);

    /**
     * 商品搜索
     *
     * @param pageUtil
     * @return
     */
    PageResult searchBooksMallGoods(PageQueryUtil pageUtil);
}
