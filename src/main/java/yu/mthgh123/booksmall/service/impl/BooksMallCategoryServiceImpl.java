package yu.mthgh123.booksmall.service.impl;

import yu.mthgh123.booksmall.common.Constants;
import yu.mthgh123.booksmall.common.BooksMallCategoryLevelEnum;
import yu.mthgh123.booksmall.common.ServiceResultEnum;
import yu.mthgh123.booksmall.controller.vo.BooksMallIndexCategoryVO;
import yu.mthgh123.booksmall.controller.vo.SearchPageCategoryVO;
import yu.mthgh123.booksmall.controller.vo.SecondLevelCategoryVO;
//import yu.mthgh123.booksmall.controller.vo.ThirdLevelCategoryVO;
import yu.mthgh123.booksmall.dao.GoodsCategoryMapper;
import yu.mthgh123.booksmall.entity.GoodsCategory;
import yu.mthgh123.booksmall.service.BooksMallCategoryService;
import yu.mthgh123.booksmall.util.BeanUtil;
import yu.mthgh123.booksmall.util.PageQueryUtil;
import yu.mthgh123.booksmall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class BooksMallCategoryServiceImpl implements BooksMallCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public PageResult getCategorisPage(PageQueryUtil pageUtil) {
        //统一一次性查询，而不是for循环单条查询
        List<GoodsCategory> goodsCategories = goodsCategoryMapper.findGoodsCategoryList(pageUtil);
        int total = goodsCategoryMapper.getTotalGoodsCategories(pageUtil);
        PageResult pageResult = new PageResult(goodsCategories, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveCategory(GoodsCategory goodsCategory) {
        //检查在同一categoryLevel是否有同名分类
        GoodsCategory temp = goodsCategoryMapper.selectByLevelAndName(goodsCategory.getCategoryLevel(), goodsCategory.getCategoryName());
        if (temp != null) {
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        //当上方检查在同一categoryLevel没有同名分类时，将数据插入数据库中
        if (goodsCategoryMapper.insertSelective(goodsCategory) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateGoodsCategory(GoodsCategory goodsCategory) {
        GoodsCategory temp = goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getCategoryId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        GoodsCategory temp2 = goodsCategoryMapper.selectByLevelAndName(goodsCategory.getCategoryLevel(), goodsCategory.getCategoryName());
        if (temp2 != null && !temp2.getCategoryId().equals(goodsCategory.getCategoryId())) {
            //同名且不同id 不能继续修改
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        goodsCategory.setUpdateTime(new Date());
        if (goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public GoodsCategory getGoodsCategoryById(Long id) {
        return goodsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //删除分类数据
        return goodsCategoryMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<BooksMallIndexCategoryVO> getCategoriesForIndex() {
        List<BooksMallIndexCategoryVO> booksMallIndexCategoryVOS = new ArrayList<>();
        //获取一级分类的固定数量的数据
        List<GoodsCategory> firstLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), BooksMallCategoryLevelEnum.LEVEL_ONE.getLevel(), Constants.INDEX_CATEGORY_NUMBER);
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            List<Long> firstLevelCategoryIds = firstLevelCategories.stream().map(GoodsCategory::getCategoryId).collect(Collectors.toList());
            //获取二级分类的数据
            List<GoodsCategory> secondLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(firstLevelCategoryIds, BooksMallCategoryLevelEnum.LEVEL_TWO.getLevel(), 0);
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                //根据 parentId 将 secondLevelCategories 分组
                /*
                将二级分类的数据存储为Map形式，以便后续在将二级分类数据插入到一级VO类时，通过：
                List<GoodsCategory> tempGoodsCategories = secondLevelCategoryMap.get(firstLevelCategory.getCategoryId());
                上述所示代码提取二级分类数据，但在插入到一级VO类之前，需要先将GoodsCategory类型的数据转化为SecondLevelCategoryVO.class类，转化代码为：
                secondLevelCategoryVO.setThirdLevelCategoryVOS((BeanUtil.copyList(tempGoodsCategories, SecondLevelCategoryVO.class)));
                */
                Map<Long, List<GoodsCategory>> secondLevelCategoryMap = secondLevelCategories.stream().collect(groupingBy(GoodsCategory::getParentId));
                for (GoodsCategory firstLevelCategory : firstLevelCategories) {
                    BooksMallIndexCategoryVO booksMallIndexCategoryVO = new BooksMallIndexCategoryVO();
                    BeanUtil.copyProperties(firstLevelCategory, booksMallIndexCategoryVO);
                    if (secondLevelCategoryMap.containsKey(firstLevelCategory.getCategoryId())) {
                        //根据一级分类的id取出secondLevelCategoryVOMap分组中的二级级分类list
                        List<GoodsCategory> tempGoodsCategories = secondLevelCategoryMap.get(firstLevelCategory.getCategoryId());
                        booksMallIndexCategoryVO.setSecondLevelCategoryVOS((BeanUtil.copyList(tempGoodsCategories, SecondLevelCategoryVO.class)));
                        booksMallIndexCategoryVOS.add(booksMallIndexCategoryVO);
                    }
                }
            }
            return booksMallIndexCategoryVOS;
        } else {
            return null;
        }
    }

    @Override
    public SearchPageCategoryVO getCategoriesForSearch(Long categoryId) {
        SearchPageCategoryVO searchPageCategoryVO = new SearchPageCategoryVO();
        GoodsCategory thirdLevelGoodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
        if (thirdLevelGoodsCategory != null && thirdLevelGoodsCategory.getCategoryLevel() == BooksMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            //获取当前三级分类的二级分类
            GoodsCategory secondLevelGoodsCategory = goodsCategoryMapper.selectByPrimaryKey(thirdLevelGoodsCategory.getParentId());
            if (secondLevelGoodsCategory != null && secondLevelGoodsCategory.getCategoryLevel() == BooksMallCategoryLevelEnum.LEVEL_TWO.getLevel()) {
                //获取当前二级分类下的三级分类List
                List<GoodsCategory> thirdLevelCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelGoodsCategory.getCategoryId()), BooksMallCategoryLevelEnum.LEVEL_THREE.getLevel(), Constants.SEARCH_CATEGORY_NUMBER);
                searchPageCategoryVO.setCurrentCategoryName(thirdLevelGoodsCategory.getCategoryName());
                searchPageCategoryVO.setSecondLevelCategoryName(secondLevelGoodsCategory.getCategoryName());
                searchPageCategoryVO.setThirdLevelCategoryList(thirdLevelCategories);
                return searchPageCategoryVO;
            }
        }
        return null;
    }

    @Override
    public List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel) {
        //number指定查询条数，当number>0时会在sql的查询语句中使用，当number=0时，由sql中的条件判断语句指定不予使用并且查询所有结果
        return goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(parentIds, categoryLevel, 0);//0代表查询所有
    }
}
