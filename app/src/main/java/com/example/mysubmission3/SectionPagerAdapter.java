package com.example.mysubmission3;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mysubmission3.listmovies.MovieFragment;
import com.example.mysubmission3.listtvshow.pojo.TvShowFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final  int[] TAB_TITLES = new int[]{R.string.tab_movies,R.string.tab_tv_shows};
    private final Context mcontext;
    private MovieFragment movieFragment;
    private TvShowFragment tvShowFragment;


    public SectionPagerAdapter(Context context,@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mcontext =  context;
        movieFragment = new MovieFragment();
        tvShowFragment = new TvShowFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return movieFragment;
        }else {
            return tvShowFragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mcontext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
