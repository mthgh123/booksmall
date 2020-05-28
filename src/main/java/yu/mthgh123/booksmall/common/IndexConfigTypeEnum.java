package yu.mthgh123.booksmall.common;

/**
 * @author yu
 * @link https://github.com/
 * @apiNote 首页配置项 1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热门推荐 4-(首页)新书上线
 */
public enum IndexConfigTypeEnum {

    DEFAULT(0, "DEFAULT"),
    INDEX_SEARCH_HOTS(1, "INDEX_SEARCH_HOTS"),
    INDEX_SEARCH_DOWN_HOTS(2, "INDEX_SEARCH_DOWN_HOTS"),
    INDEX_BOOKS_HOT(3, "INDEX_BOOKS_HOT"),
    INDEX_BOOKS_NEW(4, "INDEX_BOOKS_NEW");

    private int type;

    private String name;

    IndexConfigTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static IndexConfigTypeEnum getIndexConfigTypeEnumByType(int type) {
        for (IndexConfigTypeEnum indexConfigTypeEnum : IndexConfigTypeEnum.values()) {
            if (indexConfigTypeEnum.getType() == type) {
                return indexConfigTypeEnum;
            }
        }
        return DEFAULT;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
