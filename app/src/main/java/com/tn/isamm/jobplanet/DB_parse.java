package com.tn.isamm.jobplanet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DB_parse extends AppCompatActivity {

    SessionManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_parse);
        TextView tv_titre, tv_date, tv_desc, tv_auteur;

        manager = new SessionManager();


        Intent iIdentifikasi = getIntent();
        String msg_titre = iIdentifikasi.getStringExtra("datatitre");
        String msg_date = iIdentifikasi.getStringExtra("dataDate");
        String msg_desc = iIdentifikasi.getStringExtra("dataDesc");
        String msg_nom = iIdentifikasi.getStringExtra("dataNom");
        String msg_prennom = iIdentifikasi.getStringExtra("dataPrenom");

        tv_titre = (TextView) findViewById(R.id.textView2);
        tv_date = (TextView) findViewById(R.id.tvdate);
        tv_desc = (TextView) findViewById(R.id.tvDesc);
        tv_auteur = (TextView) findViewById(R.id.tvAuteur);

        tv_titre.setText(msg_titre);
        tv_date.setText(msg_date);
        tv_desc.setText(msg_desc);
        tv_auteur.setText(msg_nom + " " + msg_prennom);

    }
}

