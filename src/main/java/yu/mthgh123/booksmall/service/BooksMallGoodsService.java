package yu.mthgh123.booksmall.service;

import org.springframework.cache.annotation.Cacheable;
import yu.mthgh123.booksmall.entity.BooksMallGoods;
import yu.mthgh123.booksmall.util.PageQueryUtil;
import yu.mthgh123.booksmall.util.PageResult;

import java.util.List;
import java.util.Map;

public interface BooksMallGoodsService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    @Cacheable(key ="'getBooksList_'+#currentPage+#limit",value ="getBooksList")
    PageResult getBooksMallGoodsPage(PageQueryUtil pageUtil);

    /**
     * 通过Es获取书籍数据
     * @param keywords
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Cacheable(key ="'esSearchBooksList'+keywords+#pageNum+#pageSize",value ="getBooksList")
    List<Map<String,Object>> esSearchBooksList(String keywords, int pageNum, int pageSize);

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
