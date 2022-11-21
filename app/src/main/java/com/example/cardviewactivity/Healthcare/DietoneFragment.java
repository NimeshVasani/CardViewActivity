package com.example.cardviewactivity.Healthcare;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cardviewactivity.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DietoneFragment extends Fragment {


    TextView p1;

    public DietoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dietone, container, false);

    }

}
