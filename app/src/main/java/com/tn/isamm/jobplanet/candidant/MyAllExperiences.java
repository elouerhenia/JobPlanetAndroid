package com.tn.isamm.jobplanet.candidant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.tn.isamm.jobplanet.R;
import com.tn.isamm.jobplanet.SessionManager;
import com.tn.isamm.jobplanet.model.CandidatModel;
import com.tn.isamm.jobplanet.model.ExperienceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rihab on 07/12/2017.
 */

public class MyAllExperiences extends AppCompatActivity {

    // Log tag

    private ProgressDialog pDialog;
    private List<ExperienceModel> spotList = new ArrayList<ExperienceModel>();
    private ListView listView;
    private CustomListExpAdapter adapter;


    SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_all_experiences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyAllExperiences.this, AjoutOneExperience.class);
                startActivity(intent);
            }
        });


        listView = (ListView) findViewById(R.id.listfor_exp_for_certif);


        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage(getResources().getString(R.string.tv_chargement));
        pDialog.show();


        // Creating volley request obj
        JsonArrayRequest spotReq = new JsonArrayRequest(EndPoints.URL_GET_MY_EXPERIENCES,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        hidePDialog();
                        spotList.clear();
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                ExperienceModel monSpot = new ExperienceModel();

                                monSpot.setExperienceId(Integer.parseInt(obj.getString(EndPoints.KEY_EXP_ID)));
                                monSpot.setNom_org(obj.getString(EndPoints.KEY_EXP_ID));
                                monSpot.setTitrepost(obj.getString(EndPoints.KEY_EXP_ID));
                                monSpot.setDescription_exp(obj.getString(EndPoints.KEY_EXP_ID));
                                monSpot.setDate_debuEx(obj.getString(EndPoints.KEY_EXP_ID));
                                monSpot.setDae_finEx(obj.getString(EndPoints.KEY_EXP_ID));
                                // monSpot.setCandidat(obj.getString(EndPoints.KEY_EXP_ID));



                             /*   private Long experienceId;
                                private String nom_org ;
                                private String titrepost;
                                private String description_exp ;
                                private String date_debuEx;
                                private String dae_finEx;
                                private CandidatModel candidat;*/


                                spotList.add(monSpot);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data

                        adapter = new CustomListExpAdapter(MyAllExperiences.this, spotList);
                        listView.setAdapter(adapter);


                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("experiences", "Error: " + error.getMessage());
                hidePDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                // params.put(KEY_IMAGE, mImage);
                //   params.put("id",idfromsession);
                return params;
            }


        };
        ;

        // Adding request to request queue
        // AppController.getInstance().addToRequestQueue(spotReq);
        RequestQueue requestQueue = Volley.newRequestQueue(MyAllExperiences.this);
        requestQueue.add(spotReq);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
