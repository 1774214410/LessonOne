package com.liuzw.common.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/6 17:19
 */
@ApiModel(value = "分页对象",description = "分页对象")
public class PageData<T> extends ReturnBase{

    /** 总记录数 **/
    @ApiModelProperty(value = "总记录数",example = "总记录数")
    private int total = 0;

    /** 当前页 **/
    @ApiModelProperty(value = "当前页",example = "当前页")
    private int currentPage = 0;

    @ApiModelProperty(value = "总页数",example = "总页数")
    private int totalPages = 0;

    /** 数据列表 **/
    @ApiModelProperty(value = "数据列表",example = "数据列表")
    private List<T> dataList = null;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
