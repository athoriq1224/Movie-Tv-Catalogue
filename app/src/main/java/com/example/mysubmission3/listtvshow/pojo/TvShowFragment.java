package com.example.mysubmission3.listtvshow.pojo;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.example.mysubmission3.listtvshow.pojo.detail.Detail_TvShow;
import com.example.mysubmission3.listtvshow.pojo.pojo.ResponseTvShow;
import com.example.mysubmission3.listtvshow.pojo.pojo.ResultsItem;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {


    private RecyclerView recyclerView;
    private AlertDialog alertDialog;
    private ProgressBar progressBar;
    private ListTvShowsAdapter mAdapter;

    private Observer<List<ResultsItem>> getTvShows = new Observer<List<ResultsItem>>() {
        @Override
        public void onChanged(List<ResultsItem> resultsItems) {
            if (resultsItems != null) {
                mAdapter.setData(resultsItems);
            } else {
                alertDialog.setMessage(getContext().getResources().getString(R.string.server_error));
                alertDialog.show();
            }

            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return getView() != null ? getView() :
                inflater.inflate(R.layout.fragment_tv_show, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListTvShowViewModel mViewModel = ViewModelProviders.of
                (this).get(ListTvShowViewModel.class);
        mViewModel.getResponseTvShows().observe(this,getTvShows);

        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progressBar);

        alertDialog = new AlertDialog.Builder(view.getContext()).setTitle(getString(R.string.failure))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();

        mAdapter = new ListTvShowsAdapter();
        mAdapter.notifyDataSetChanged();

        mViewModel.setResponseTvShows();
        showListData();
    }

    void showListData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter.SetOnItemClickListener(new ListTvShowsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ResultsItem model) {
                Intent gtd = new Intent(view.getContext(), Detail_TvShow.class);
                gtd.putExtra(Detail_TvShow.SELECTED_TV_SHOWS, model);
                startActivity(gtd);
            }
        });

        recyclerView.setAdapter(mAdapter);
    }
}

