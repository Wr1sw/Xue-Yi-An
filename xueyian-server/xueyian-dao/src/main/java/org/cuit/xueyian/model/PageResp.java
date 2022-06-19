package org.cuit.xueyian.model;

import java.util.List;

public class PageResp<T> {
    private Long total;

    private List<T> data;

    public PageResp(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public PageResp() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
