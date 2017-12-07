package com.tn.isamm.jobplanet.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tn.isamm.jobplanet.R;
import com.tn.isamm.jobplanet.SessionManager;
import com.tn.isamm.jobplanet.admin.MainActivityAdmin;
import com.tn.isamm.jobplanet.candidant.MainActivityCandidat;
import com.tn.isamm.jobplanet.recruteur.MainActivityRecruteur;

public class ChoixActivity extends AppCompatActivity {

    SessionManager manager;

    private Button choix_btn_admin;
    private Button choix_btn_recruteur;
    private Button choix_btn_candidat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);

        manager = new SessionManager();


        choix_btn_admin = (Button) findViewById(R.id.choix_activity_admin);
        choix_btn_recruteur = (Button) findViewById(R.id.choix_activity_recruteur);
        choix_btn_candidat = (Button) findViewById(R.id.choix_activity_candidat);

        String status = manager.getPreferences(ChoixActivity.this, "status");
        String help = manager.getPreferences(ChoixActivity.this, "help");

        Log.d("status", status);
        if (status.equals("1")) {

            choix_btn_admin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.setPreferences(ChoixActivity.this, "compte", "admin");
                    Intent itent = new Intent(ChoixActivity.this, MainActivityAdmin.class);
                    startActivity(itent);

                }
            });

            choix_btn_recruteur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.setPreferences(ChoixActivity.this, "compte", "recruteur");
                    Intent itent = new Intent(ChoixActivity.this, MainActivityRecruteur.class);
                    startActivity(itent);


                }
            });

            choix_btn_candidat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.setPreferences(ChoixActivity.this, "compte", "candidat");
                    Intent itent = new Intent(ChoixActivity.this, MainActivityCandidat.class);
                    startActivity(itent);


                }
            });

        } else {
            choix_btn_admin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.setPreferences(ChoixActivity.this, "compte", "admin");
                    Intent itent = new Intent(ChoixActivity.this, LoginActivity.class);
                    startActivity(itent);

                }
            });

            choix_btn_recruteur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.setPreferences(ChoixActivity.this, "compte", "recruteur");
                    Intent itent = new Intent(ChoixActivity.this, LoginActivity.class);
                    startActivity(itent);


                }
            });

            choix_btn_candidat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.setPreferences(ChoixActivity.this, "compte", "candidat");
                    Intent itent = new Intent(ChoixActivity.this, LoginActivity.class);
                    startActivity(itent);


                }
            });
        }


    }


}
