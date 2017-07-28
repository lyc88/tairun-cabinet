package com.tairun.server.utils;

import java.util.List;

/**
 * Created by lyc on 2017/7/26.
 */
public class EUDataGridResult {

    private long total;
    private List<?> rows;
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
