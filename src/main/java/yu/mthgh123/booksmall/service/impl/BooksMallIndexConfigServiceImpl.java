package yu.mthgh123.booksmall.service.impl;

import yu.mthgh123.booksmall.common.ServiceResultEnum;
import yu.mthgh123.booksmall.controller.vo.BooksMallIndexConfigGoodsVO;
import yu.mthgh123.booksmall.dao.BooksMallGoodsMapper;
import yu.mthgh123.booksmall.dao.IndexConfigMapper;
import yu.mthgh123.booksmall.entity.BooksMallGoods;
import yu.mthgh123.booksmall.entity.IndexConfig;
import yu.mthgh123.booksmall.service.BooksMallIndexConfigService;
import yu.mthgh123.booksmall.util.BeanUtil;
import yu.mthgh123.booksmall.util.PageQueryUtil;
import yu.mthgh123.booksmall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksMallIndexConfigServiceImpl implements BooksMallIndexConfigService {

    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Autowired
    private BooksMallGoodsMapper goodsMapper;

    @Override
    public PageResult getConfigsPage(PageQueryUtil pageUtil) {
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigList(pageUtil);
        int total = indexConfigMapper.getTotalIndexConfigs(pageUtil);
        PageResult pageResult = new PageResult(indexConfigs, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveIndexConfig(IndexConfig indexConfig) {
        //todo 判断是否存在该商品
        if (indexConfigMapper.insertSelective(indexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateIndexConfig(IndexConfig indexConfig) {
        //todo 判断是否存在该商品
        IndexConfig temp = indexConfigMapper.selectByPrimaryKey(indexConfig.getConfigId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        if (indexConfigMapper.updateByPrimaryKeySelective(indexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public IndexConfig getIndexConfigById(Long id) {
        return null;
    }

    @Override
    public List<BooksMallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number) {
        List<BooksMallIndexConfigGoodsVO> booksMallIndexConfigGoodsVOS = new ArrayList<>(number);
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigsByTypeAndNum(configType, number);
        if (!CollectionUtils.isEmpty(indexConfigs)) {
            //取出所有的goodsId
            List<Long> goodsIds = indexConfigs.stream().map(IndexConfig::getGoodsId).collect(Collectors.toList());
            List<BooksMallGoods> booksMallGoods = goodsMapper.selectByPrimaryKeys(goodsIds);
            //将查找出的所有List<BooksMallGoods>数据booksMallGoods转化为VO对象
            booksMallIndexConfigGoodsVOS = BeanUtil.copyList(booksMallGoods, BooksMallIndexConfigGoodsVO.class);
            for (BooksMallIndexConfigGoodsVO booksMallIndexConfigGoodsVO : booksMallIndexConfigGoodsVOS) {
                String goodsName = booksMallIndexConfigGoodsVO.getGoodsName();
                String author = booksMallIndexConfigGoodsVO.getAuthor();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 20) {
                    goodsName = goodsName.substring(0, 20) + "...";
                    booksMallIndexConfigGoodsVO.setGoodsName(goodsName);
                }
                if (author.length() > 20) {
                    author = author.substring(0, 20) + "...";
                    booksMallIndexConfigGoodsVO.setAuthor(author);
                }
            }
        }
        return booksMallIndexConfigGoodsVOS;
    }

    @Override
    public Boolean deleteBatch(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //删除数据
        return indexConfigMapper.deleteBatch(ids) > 0;
    }
}
