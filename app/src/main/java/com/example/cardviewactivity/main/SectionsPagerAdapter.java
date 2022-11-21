package com.example.cardviewactivity.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cardviewactivity.Healthcare.DietfourFragment;
import com.example.cardviewactivity.Healthcare.DietoneFragment;
import com.example.cardviewactivity.Healthcare.DietthreeFragment;
import com.example.cardviewactivity.Healthcare.DiettwoFragment;
import com.example.cardviewactivity.R;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.diet1,R.string.diet2,R.string.diet3,R.string.diet4};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

      Fragment fragment=null;

      switch (position)
      {
          case 0:
              fragment=new DietoneFragment();
              break;

          case 1:
              fragment=new DiettwoFragment();
              break;

          case 2:
              fragment=new DietthreeFragment();
              break;

          case 3:
              fragment=new DietfourFragment();
              break;
      }
       return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 4;
    }
}