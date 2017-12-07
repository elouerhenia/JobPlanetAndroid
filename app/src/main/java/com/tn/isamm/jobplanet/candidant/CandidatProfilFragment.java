package com.tn.isamm.jobplanet.candidant;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tn.isamm.jobplanet.R;


public class CandidatProfilFragment extends Fragment {

    private Button login_button, gotoregister;


    // Log tag
    private static final String TAG = CandidatProfilFragment.class.getSimpleName();
    View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static CandidatProfilFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.candidat_fragment_profil, container, false);

        fragment = this;
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        Button btn_goto_coordonnees_perso = (Button) rootView.findViewById(R.id.btn_go_to_coordonne_personnel);
        btn_goto_coordonnees_perso.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CoordonneesPersonnels.class);
                startActivity(intent);
            }
        });


        Button btn_goto_experiences = (Button) rootView.findViewById(R.id.btn_goto_experience);
        btn_goto_experiences.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AjoutOneExperience.class);
                startActivity(intent);
            }
        });


        Button btn_goto_formations = (Button) rootView.findViewById(R.id.btn_go_to_formation);
        btn_goto_formations.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Formations.class);
                startActivity(intent);
            }
        });


        Button btn_goto_certif = (Button) rootView.findViewById(R.id.btn_go_to_certif);
        btn_goto_certif.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Certificats.class);
                startActivity(intent);
            }
        });


        Button btn_goto_upload_cv = (Button) rootView.findViewById(R.id.btn_go_to_uploadcv);
        btn_goto_upload_cv.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UploadCV.class);
                startActivity(intent);
            }
        });


        Button btn_goto_upload_lettre_motivation = (Button) rootView.findViewById(R.id.btn_go_to_upload_lettre);
        btn_goto_upload_lettre_motivation.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UploadLettre.class);
                startActivity(intent);
            }
        });


        return rootView;
    }

    public static CandidatProfilFragment newInstance() {
        if (fragment == null) {

            fragment = new CandidatProfilFragment();
        }
        return fragment;
    }


}
