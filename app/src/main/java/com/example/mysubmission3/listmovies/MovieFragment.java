package com.example.mysubmission3.listmovies;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mysubmission3.R;
import com.example.mysubmission3.listmovies.detail.Detail_movie;
import com.example.mysubmission3.listmovies.pojo.ResponseMovie;
import com.example.mysubmission3.listmovies.pojo.ResultsItem;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private android.app.AlertDialog alertDialog;
    private ListMoviesAdapter mAdapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getView() != null ? getView() :
                inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListMoviesViewModel mViewModel = ViewModelProviders.of(this).get(ListMoviesViewModel.class);
        mViewModel.getMovies().observe(this,getMovies);

        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progressBar);

        mAdapter = new ListMoviesAdapter();
        mAdapter.notifyDataSetChanged();

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();

        mViewModel.doRequestListMovies();
        showListData();
    }

    private void showListData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mAdapter.SetOnItemClickListener(new ListMoviesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ResultsItem model) {
                Intent GDM = new Intent(view.getContext(), Detail_movie.class);
                GDM.putExtra(Detail_movie.SELECTED_MOVIE, model);
                startActivity(GDM);
            }
        });

        recyclerView.setAdapter(mAdapter);
    }

    private Observer<List<ResultsItem>> getMovies = new Observer<List<ResultsItem>>() {
        @Override
        public void onChanged(List<ResultsItem> resultsItems) {
            if (resultsItems!=null) {

                mAdapter.setdata(resultsItems);
                progressBar.setVisibility(View.GONE);
            }
        }
    };
}
