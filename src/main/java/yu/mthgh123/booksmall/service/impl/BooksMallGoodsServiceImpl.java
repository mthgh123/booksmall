package yu.mthgh123.booksmall.service.impl;

import yu.mthgh123.booksmall.common.ServiceResultEnum;
import yu.mthgh123.booksmall.controller.vo.BooksMallSearchGoodsVO;
import yu.mthgh123.booksmall.dao.BooksMallGoodsMapper;
import yu.mthgh123.booksmall.entity.BooksMallGoods;
import yu.mthgh123.booksmall.service.BooksMallGoodsService;
import yu.mthgh123.booksmall.util.BeanUtil;
import yu.mthgh123.booksmall.util.PageQueryUtil;
import yu.mthgh123.booksmall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BooksMallGoodsServiceImpl implements BooksMallGoodsService {

    @Autowired
    private BooksMallGoodsMapper goodsMapper;

    @Override
    public PageResult getBooksMallGoodsPage(PageQueryUtil pageUtil) {
        List<BooksMallGoods> goodsList = goodsMapper.findBooksMallGoodsList(pageUtil);
        int total = goodsMapper.getTotalBooksMallGoods(pageUtil);
        PageResult pageResult = new PageResult(goodsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveBooksMallGoods(BooksMallGoods goods) {
        if (goodsMapper.insertSelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public void batchSaveBooksMallGoods(List<BooksMallGoods> booksMallGoodsList) {
        if (!CollectionUtils.isEmpty(booksMallGoodsList)) {
            goodsMapper.batchInsert(booksMallGoodsList);
        }
    }

    @Override
    public String updateBooksMallGoods(BooksMallGoods goods) {
        //先查看选择的书籍在数据库中是否存在
        BooksMallGoods temp = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        //当查询到有相应书籍时，进行下面的操作
        goods.setUpdateTime(new Date());
        if (goodsMapper.updateByPrimaryKeySelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public BooksMallGoods getBooksMallGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return goodsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }

    //涉及到书籍检索的service层代码
    @Override
    public PageResult searchBooksMallGoods(PageQueryUtil pageUtil) {
        List<BooksMallGoods> goodsList = goodsMapper.findBooksMallGoodsListBySearch(pageUtil);
        int total = goodsMapper.getTotalBooksMallGoodsBySearch(pageUtil);
        List<BooksMallSearchGoodsVO> booksMallSearchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsList)) {
            booksMallSearchGoodsVOS = BeanUtil.copyList(goodsList, BooksMallSearchGoodsVO.class);
            for (BooksMallSearchGoodsVO booksMallSearchGoodsVO : booksMallSearchGoodsVOS) {
                String goodsName = booksMallSearchGoodsVO.getGoodsName();
                String goodsIntro = booksMallSearchGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    booksMallSearchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    booksMallSearchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        PageResult pageResult = new PageResult(booksMallSearchGoodsVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
