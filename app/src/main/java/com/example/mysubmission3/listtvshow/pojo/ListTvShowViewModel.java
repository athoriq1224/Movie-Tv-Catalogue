package com.example.mysubmission3.listtvshow.pojo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.mysubmission3.MovieCataloge;
import com.example.mysubmission3.listtvshow.pojo.pojo.ResponseTvShow;
import com.example.mysubmission3.listtvshow.pojo.pojo.ResultsItem;

import java.util.List;

public class ListTvShowViewModel extends ViewModel {
    private MutableLiveData<List<ResultsItem>> responseTvShows = new MutableLiveData<>();

    // mengambil data dari fragment
    public MutableLiveData<List<ResultsItem>> getResponseTvShows() {
        if (responseTvShows == null) {
            setResponseTvShows();
        }
        return responseTvShows;
    }

    public void setResponseTvShows() {
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/tv")
                .addQueryParameter("api_key", MovieCataloge.MOVIE_DB_API_KEY)
                .addQueryParameter("language", "en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseTvShow.class, new ParsedRequestListener<ResponseTvShow>() {

                    @Override
                    public void onResponse(ResponseTvShow response) {
                        responseTvShows.postValue(response.getResults());
                        Log.d("ViewModelTV", "onResponse: " + response.toString());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("ViewModelTV", "onError: " + anError.getMessage());
                    }
                });
    }
}