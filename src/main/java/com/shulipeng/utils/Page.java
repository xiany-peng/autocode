package com.shulipeng.utils;

import java.util.List;

public class Page {

    /**
     * 总条数
     */
    private int total;
    @SuppressWarnings("rawtypes")
    private List rows;
    private int pageNumber;
    private int pageSize = 15;

    public Page(){}

    @SuppressWarnings("rawtypes")
    public Page(String total,List rows){
        this.total = Integer.parseInt(total);
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = Integer.valueOf(total);
    }
    @SuppressWarnings("rawtypes")
    public List getRows() {
        return rows;
    }
    @SuppressWarnings("rawtypes")
    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if(pageNumber==0){
            this.pageNumber = 1;
        }else{
            this.pageNumber = pageNumber+1;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
