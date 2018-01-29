package com.blogger.model.request;

import com.blogger.model.Test;

public class TestRequest extends Test{

    private String sort;
    private String sortModel;

    public String getSortModel() {
        return sortModel;
    }

    public void setSortModel(String sortModel) {
        this.sortModel = sortModel;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
