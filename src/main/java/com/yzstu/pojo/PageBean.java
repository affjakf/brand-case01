package com.yzstu.pojo;

import java.util.List;

/**
 *
 */
public class PageBean<T> {
    //当前页数据
    private List<T> rows;
    //总记录数
    private int totalCount;

    public PageBean() {
    }

    public PageBean(List<T> rows, int totalCount) {
        this.rows = rows;
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
