package com.tn.isamm.jobplanet.candidant;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tn.isamm.jobplanet.R;


public class DeposeCVFragment extends Fragment {

    // Log tag
    private static final String TAG = DeposeCVFragment.class.getSimpleName();
    View rootView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static DeposeCVFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.depose_cv_fragment, container, false);

        fragment = this;
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        return rootView;
    }


    public static DeposeCVFragment newInstance() {
        if (fragment == null) {

            fragment = new DeposeCVFragment();
        }
        return fragment;
    }


}
