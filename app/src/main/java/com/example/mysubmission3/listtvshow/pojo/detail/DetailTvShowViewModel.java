package com.example.mysubmission3.listtvshow.pojo.detail;

import androidx.lifecycle.ViewModel;

import com.example.mysubmission3.listtvshow.pojo.pojo.ResultsItem;

public class DetailTvShowViewModel  extends ViewModel {
    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(ResultsItem resultsItem) {
        this.resultsItem = resultsItem;
    }
}
