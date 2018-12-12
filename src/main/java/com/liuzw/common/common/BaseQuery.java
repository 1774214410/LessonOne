package com.liuzw.common.common;

/**
 * 公共查询基类
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/7 22:31
 */
public class BaseQuery {

    private static final int DEFAULT_PAGE_SIZE = 10;

    protected int pageNum = 1;

    protected int pageSize = DEFAULT_PAGE_SIZE;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取页码
     * @return 页码
     */
    public int getPageNumOrDefault() {
        return pageNum <= 0 ? 1 : pageNum;
    }

    /**
     * 获取每页数量
     * @return 每页数量
     */
    public int getPageSizeOrDefault() {
        return pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
    }

    /**
     * 获取分页开始记录行
     * @return 分页开始记录行
     */
    public int getStart() {
        int pageSize = getPageSizeOrDefault();
        int pageNum = getPageNumOrDefault();
        return (pageNum - 1) * pageSize;
    }
}
