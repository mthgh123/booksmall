package yu.mthgh123.booksmall.dao;

import yu.mthgh123.booksmall.entity.BooksMallShoppingCartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksMallShoppingCartItemMapper {
    int deleteByPrimaryKey(Long cartItemId);

    int insert(BooksMallShoppingCartItem record);

    int insertSelective(BooksMallShoppingCartItem record);

    BooksMallShoppingCartItem selectByPrimaryKey(Long cartItemId);

    BooksMallShoppingCartItem selectByUserIdAndGoodsId(@Param("newBeeMallUserId") Long newBeeMallUserId, @Param("goodsId") Long goodsId);

    List<BooksMallShoppingCartItem> selectByUserId(@Param("newBeeMallUserId") Long newBeeMallUserId, @Param("number") int number);

    int selectCountByUserId(Long newBeeMallUserId);

    int updateByPrimaryKeySelective(BooksMallShoppingCartItem record);

    int updateByPrimaryKey(BooksMallShoppingCartItem record);

    int deleteBatch(List<Long> ids);
}