package com.yzd.jutils.httpExt_XXLJOB;

import java.util.ArrayList;
import java.util.List;

public class XXLJob_RecordsFiltered {
    private int recordsFiltered;
    private List<XXLJob_R_Data> data = new ArrayList<>();

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<XXLJob_R_Data> getData() {
        return data;
    }

    public void setData(List<XXLJob_R_Data> data) {
        this.data = data;
    }
}
