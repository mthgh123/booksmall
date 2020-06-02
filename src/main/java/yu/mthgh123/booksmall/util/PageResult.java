package yu.mthgh123.booksmall.util;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @author yu
 * @link https://github.com/mthgh123/booksmall
 */
public class PageResult implements Serializable {

    //总记录数
    private int totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int currPage;
    //列表数据
    private List<?> list;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageResult(List<?> list, int totalCount, int pageSize, int currPage) {
        //list数据和totalCount数据是真正的从数据库读取出来的，其中totalCount只是读取存储的数据的条数
        this.list = list;
        this.totalCount = totalCount;
        //pageSize其实也是从前端jqgird的prmNames中的page传给server（后端），然后在service层直接取用的
        this.pageSize = pageSize;
        //currPage也不是从数据库查出来的，而是由前端jqgird的prmNames中的page传给server（后端），然后在service层直接取用的
        this.currPage = currPage;
        //totalPage可以直接由totalCount和pageSize算出来，所以不需要从数据库查询，也不需要存储该数据到数据库中
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

}
