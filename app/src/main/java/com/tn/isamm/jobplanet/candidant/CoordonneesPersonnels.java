package com.tn.isamm.jobplanet.candidant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.owlike.genson.Genson;
import com.tn.isamm.jobplanet.R;
import com.tn.isamm.jobplanet.SessionManager;
import com.tn.isamm.jobplanet.activities.LoginActivity;
import com.tn.isamm.jobplanet.model.CandidatModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Rihab on 05/12/2017.
 */
public class CoordonneesPersonnels extends AppCompatActivity {


    SessionManager manager;

    private EditText input_nom_cand, input_pwd, input_repwd, input_prenom_cand, input_adresse_cand, input_code_postale, input_avatar, input_tel_cand, input_mail;
    private Button btn_update;

    String idfromsession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordonnees_personnels);

        manager = new SessionManager();
        idfromsession = manager.getPreferences(CoordonneesPersonnels.this, "id");


        input_nom_cand = (EditText) findViewById(R.id.register_nom_cand);
        input_prenom_cand = (EditText) findViewById(R.id.register_prenom_cand);
        input_adresse_cand = (EditText) findViewById(R.id.register_adresse_cand);
        input_code_postale = (EditText) findViewById(R.id.register_code_postale);
        input_avatar = (EditText) findViewById(R.id.register_avatar);
        input_tel_cand = (EditText) findViewById(R.id.register_tel_cand);
        input_mail = (EditText) findViewById(R.id.register_mail);
        input_pwd = (EditText) findViewById(R.id.register_password);
        input_repwd = (EditText) findViewById(R.id.register_repassword);


        btn_update = (Button) findViewById(R.id.register_submit);
        btn_update.setOnClickListener(btnUpdateListener);


        getConnected();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    private View.OnClickListener btnUpdateListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                  /*  input_nom_cand = (EditText) findViewById(R.id.register_nom_cand);
                    input_prenom_cand = (EditText) findViewById(R.id.register_prenom_cand);
                    input_adresse_cand = (EditText) findViewById(R.id.register_adresse_cand);
                    input_code_postale = (EditText) findViewById(R.id.register_code_postale);
                    input_avatar = (EditText) findViewById(R.id.register_avatar);
                    input_tel_cand = (EditText) findViewById(R.id.register_tel_cand);
                    input_mail = (EditText) findViewById(R.id.register_mail);
                    input_pwd = (EditText) findViewById(R.id.register_password);
                    input_repwd*/


                    CandidatModel cdt = new CandidatModel(

                            input_nom_cand.getText().toString(),
                            input_prenom_cand.getText().toString(),
                            input_adresse_cand.getText().toString(),
                            Integer.parseInt(input_code_postale.getText().toString()),
                            input_avatar.getText().toString(),
                            Integer.parseInt(input_tel_cand.getText().toString()),
                            input_mail.getText().toString(),
                            input_pwd.getText().toString()
                    );
                    cdt.setCandidatId(Integer.parseInt(idfromsession));
                    String message = new Genson().serialize(cdt);
                    Log.i("SAIDDEBUG", "" + cdt);

                    HttpURLConnection urlConnection = null;
                    try {
                        URL url = new URL(EndPoints.URL_UPDATE_CAND);
                        urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("PUT");
                        urlConnection.setDoOutput(true);
                        urlConnection.setRequestProperty("Content-Type", "application/json");

                        OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                        out.write(message.getBytes());
                        out.close();

                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        Scanner scanner = new Scanner(in);

                        in.close();

                    } catch (Exception e) {
                        Log.e("SAIDEBUG", "HTTP UPDATE N'EXISTE PAS");
                    } finally {
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                    }
                }
            }).start();
            Toast.makeText(CoordonneesPersonnels.this, "Les donners sont modifier avec sucee ", Toast.LENGTH_LONG).show();
        }
    };


    public void getConnected() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_GET_ONE_CAND,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //Disimissing the progress dialog
                        // hidePDialog();


                        JSONObject root = null;
                        try {
                            root = new JSONObject(response);


                            Log.i("myid", root.getString("candidatId"));

                            input_nom_cand.setText(root.getString(EndPoints.KEY_NAME_CAND));
                            input_prenom_cand.setText(root.getString(EndPoints.KEY_PRENAME_CAND));
                            input_code_postale.setText(String.valueOf(root.getString(EndPoints.KEY_CODE_POSTAL_CAND)));
                            input_mail.setText(root.getString(EndPoints.KEY_EMAIL_CAND));
                            input_tel_cand.setText(String.valueOf(root.getString(EndPoints.KEY_TEL_CAND)));
                            input_adresse_cand.setText(root.getString(EndPoints.KEY_ADRESSE_CAND));
                            input_avatar.setText(root.getString(EndPoints.KEY_AVATAR_CAND));
                            input_pwd.setText(root.getString(EndPoints.KEY_PWD_CAND));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //hidePDialog();

                        Toast.makeText(CoordonneesPersonnels.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                // params.put(KEY_IMAGE, mImage);
                params.put("id", idfromsession);
                return params;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(CoordonneesPersonnels.this);
        requestQueue.add(stringRequest);


        // return cdt;
    }
}
