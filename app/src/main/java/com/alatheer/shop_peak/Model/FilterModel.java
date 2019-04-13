package com.alatheer.shop_peak.Model;

import java.util.List;

/**
 * Created by M.Hamada on 13/04/2019.
 */

public class FilterModel {
    String title;
    List<FilterModelDetails>filterModelDetailsList;

    public FilterModel(String title, List<FilterModelDetails> filterModelDetailsList) {
        this.title = title;
        this.filterModelDetailsList = filterModelDetailsList;
    }

    public List<FilterModelDetails> getFilterModelDetailsList() {
        return filterModelDetailsList;
    }

    public void setFilterModelDetailsList(List<FilterModelDetails> filterModelDetailsList) {
        this.filterModelDetailsList = filterModelDetailsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = title;
    }
}
