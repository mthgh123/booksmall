package yu.mthgh123.booksmall.dao;

import yu.mthgh123.booksmall.entity.BooksMallGoods;
import yu.mthgh123.booksmall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksMallGoodsMapper {
    int deleteByPrimaryKey(Long goodsId);

    int insert(BooksMallGoods record);

    int insertSelective(BooksMallGoods record);

    BooksMallGoods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(BooksMallGoods record);

    int updateByPrimaryKeyWithBLOBs(BooksMallGoods record);

    int updateByPrimaryKey(BooksMallGoods record);

    List<BooksMallGoods> findBooksMallGoodsList(PageQueryUtil pageUtil);

    int getTotalBooksMallGoods(PageQueryUtil pageUtil);

    List<BooksMallGoods> selectByPrimaryKeys(List<Long> goodsIds);

    List<BooksMallGoods> findBooksMallGoodsListBySearch(PageQueryUtil pageUtil);

    int getTotalBooksMallGoodsBySearch(PageQueryUtil pageUtil);

    int batchInsert(@Param("booksMallGoodsList") List<BooksMallGoods> booksMallGoodsList);

    int batchUpdateSellStatus(@Param("orderIds") Long[] orderIds, @Param("sellStatus") int sellStatus);

}