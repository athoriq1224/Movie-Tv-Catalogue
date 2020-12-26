package com.example.mysubmission3.listtvshow.pojo.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.mysubmission3.R;
import com.example.mysubmission3.databinding.ActivityDetailTvShowBinding;
import com.example.mysubmission3.listtvshow.pojo.pojo.ResultsItem;

import java.util.Objects;

public class Detail_TvShow extends AppCompatActivity {

    public static final String SELECTED_TV_SHOWS = "selected_tv_shows";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        DetailTvShowViewModel viewModel = ViewModelProviders.of(this).get(DetailTvShowViewModel.class);
        ActivityDetailTvShowBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail__tv_show);
        ResultsItem movieModel = getIntent().getParcelableExtra(SELECTED_TV_SHOWS);
        viewModel.setResultsItem(movieModel);

        binding.setTitle(viewModel.getResultsItem().getnama());
        binding.setOriginalLanguage(viewModel.getResultsItem().getOriginalLanguage());
        binding.setReleaseDate(viewModel.getResultsItem().getFirstAirDate());
        binding.setOverview(viewModel.getResultsItem().getOverview());
        binding.setVote(String.valueOf(viewModel.getResultsItem().getVoteCount()));

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/"+movieModel.getPosterPath()).into(binding.imgPoster);
        setTitle(viewModel.getResultsItem().getnama());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

