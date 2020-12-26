package com.example.mysubmission3.listmovies.detail;

import androidx.lifecycle.ViewModel;

import com.example.mysubmission3.listmovies.pojo.ResultsItem;

public class DetailMovieViewModel extends ViewModel {
    private ResultsItem resultsItem;

    public ResultsItem getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(ResultsItem resultsItem) {
        this.resultsItem = resultsItem;
    }
}
