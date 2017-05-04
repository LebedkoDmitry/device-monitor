package org.lebedko.device.monitor.dto;

import java.util.List;

public class RecordsDto {

    private List<Accident> records;
    private Long page;
    private Long pageSize;
    private Long total;

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPage() {

        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public List<Accident> getRecords() {

        return records;
    }

    public void setRecords(List<Accident> records) {
        this.records = records;
    }
}
