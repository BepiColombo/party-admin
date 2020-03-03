package com.tck.party.common.utils;

import lombok.Data;
import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class PageInfo<T> implements Serializable {
    private Integer pageNum;

    private Integer pageSize;

    private long total;

    private int pages;

    private List<T> list;


    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.pages = page.getPages();
            this.list = page;
            this.total = page.getTotal();
        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();
            this.pages = 1;
            this.list = list;
            this.total = list.size();
        }

    }



}
