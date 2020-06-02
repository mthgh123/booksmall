package yu.mthgh123.booksmall.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 分页查询参数
 *
 * @author yu
 * @link https://github.com/mthgh123/booksmall
 */
public class PageQueryUtil extends LinkedHashMap<String, Object> {
    //当前页码
    private int page;
    //每页条数
    private int limit;

    public PageQueryUtil(Map<String, Object> params) {
        /*已知抽象类 AbstractMap<K,V> ， HashMap<K,V> extends AbstractMap<K,V>
        HashMap<K,V>是非抽象类，HashMap<K,V>中实现了putAll方法，并且下面调用的就是
        HashMap<K,V>中实现的putAll方法，LinkedHashMap<String, Object>类是HashMap<K,V>
        的子类，并且其中没有定义putAll方法。
        */
        //将params键值对中的所有键值对内容复成为this类型所指定的键值对
        this.putAll(params);

        //通过传入的params参数可以知道由前端传来的page和limit的值，并给PageQueryUtil类的page和limit实例域设定相同对应的值
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        //给相应的键赋对应的值
        this.put("start", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
