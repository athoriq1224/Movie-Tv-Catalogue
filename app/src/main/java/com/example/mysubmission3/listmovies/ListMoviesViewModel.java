package com.example.mysubmission3.listmovies;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.mysubmission3.MovieCataloge;
import com.example.mysubmission3.listmovies.pojo.ResponseMovie;
import com.example.mysubmission3.listmovies.pojo.ResultsItem;

import java.util.List;
//import com.sennohananto.moviecatalogue.MovieCatalogue;
//import com.sennohananto.moviecatalogue.listmovies.pojo.ResponseMovies;

public class ListMoviesViewModel extends ViewModel {

    private MutableLiveData<List<ResultsItem>> responseMovies = new MutableLiveData<>();


    public MutableLiveData<List<ResultsItem>> getMovies(){
        return responseMovies;
    }

    public void doRequestListMovies(){
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/movie")
                .addQueryParameter("api_key", MovieCataloge.MOVIE_DB_API_KEY)
                .addQueryParameter("language","en-US")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ResponseMovie.class, new ParsedRequestListener<ResponseMovie>() {
                    @Override
                    public void onResponse(ResponseMovie response) {
                        if (response != null) {
                            responseMovies.postValue(response.getResults());
                            Log.d("onResponseMovie ", response.toString());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("onFailureMovie ", anError.getMessage());
                    }
                });
    }

}
