package util;

import java.util.List;

/**
 * 关于分页的工具类
 * @author
 */
public class Page<E> {
    /**
     * 当前页码
     */
    private int pageNo;
    /**
     * 每页显示数量
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 对应的分页数据集合
     */
    private List<E> rows;
    /**
     * 总页数
     */
    private int pageCount;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }

    public int getPageCount() {
        return pageCount;
    }

    /**
     * 设置总页数
     * 总页数 = （总记录数 + 每页显示数量 - 1）/每页显示数量
     */
    public void setPageCount() {
        this.pageCount = (totalCount+pageSize-1)/pageSize;
    }
}
