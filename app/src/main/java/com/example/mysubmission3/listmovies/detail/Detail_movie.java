package com.example.mysubmission3.listmovies.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.mysubmission3.R;
import com.example.mysubmission3.databinding.ActivityDetailMovieBinding;
import com.example.mysubmission3.listmovies.pojo.ResultsItem;

import java.util.Objects;

public class Detail_movie extends AppCompatActivity {
    public static final String SELECTED_MOVIE = "select movie";

    @Override
    protected void onCreate(Bundle ssavedInstanceState) {
        super.onCreate(ssavedInstanceState);

        DetailMovieViewModel detailmovieviewmodel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);
        ActivityDetailMovieBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_movie);
        ResultsItem moviemodel = getIntent().getParcelableExtra(SELECTED_MOVIE);
        detailmovieviewmodel.setResultsItem(moviemodel);
        binding.setViewmodel(detailmovieviewmodel);

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+moviemodel.getPosterPath()).into(binding.imgPoster);
        setTitle(detailmovieviewmodel.getResultsItem().getTitle());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}