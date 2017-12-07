package com.tn.isamm.jobplanet.candidant;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tn.isamm.jobplanet.R;


public class CandidatRechercheFragment extends Fragment {

    // Log tag
    private static final String TAG = CandidatRechercheFragment.class.getSimpleName();
    View rootView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static CandidatRechercheFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.candidat_fragment_recherche, container, false);

        fragment = this;
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        return rootView;
    }


    public static CandidatRechercheFragment newInstance() {
        if (fragment == null) {

            fragment = new CandidatRechercheFragment();
        }
        return fragment;
    }


}
