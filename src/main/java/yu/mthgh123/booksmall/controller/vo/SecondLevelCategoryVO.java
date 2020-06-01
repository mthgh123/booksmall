package yu.mthgh123.booksmall.controller.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 首页分类数据VO(第二级)
 */
public class SecondLevelCategoryVO implements Serializable {

    private Long categoryId;
    //第一级目录的parentId都默认设定为0
    private Long parentId;

    private Byte categoryLevel;

    private String categoryName;


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Byte getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Byte categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
