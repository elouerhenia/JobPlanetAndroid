package com.tn.isamm.jobplanet.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.tn.isamm.jobplanet.R;
import com.tn.isamm.jobplanet.candidant.EndPoints;
import com.tn.isamm.jobplanet.model.CandidatModel;
import com.tn.isamm.jobplanet.model.EntrepriseModel;
import com.tn.isamm.jobplanet.utils.AppController;
import com.tn.isamm.jobplanet.utils.CheckNetwork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_ADRESSE_CAND;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_AVATAR_CAND;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_CODE_POSTAL_CAND;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_DESCRIPTION_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_EFFECTIF_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_EMAIL_CAND;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_EMAIL_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_ETAT_COMPTE_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_FONDATION_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_LOGIN_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_LOGO_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_NOM_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_POPULARITE_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_PWD_CAND;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_PWD_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_SECTEUR_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_SIEGE_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_SITE_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_TEL_CAND;


public class AdminAllCandidatFragment extends android.support.v4.app.Fragment {


    // Log tag
    private static final String TAG = AdminAllCandidatFragment.class.getSimpleName();

    private ProgressDialog pDialog;
    private List<CandidatModel> candidatsList = new ArrayList<CandidatModel>();
    private ListView listViewCandidat;
    private CustomListCandidatAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static AdminAllCandidatFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_all_entreprise, container, false);

        fragment = this;

        //((AppCompatActivity) getActivity()).getSupportActionBar().show();
        //((AppCompatActivity) getActivity()).getSupportActionBar().setShowHideAnimationEnabled(false);

        listViewCandidat = (ListView) rootView.findViewById(R.id.list);
        adapter = new CustomListCandidatAdapter(getActivity(), candidatsList);
        listViewCandidat.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage(getResources().getString(R.string.tv_chargement));
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest billionaireReq = new JsonArrayRequest(EndPoints.URL_GET_ALL_CANDIDATS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        candidatsList.clear();
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                CandidatModel cdt = new CandidatModel();
                                /*  private String nom_cand;
                                private String prenom_cand;
                                private String adresse_cand;
                                private int code_postale;
                                private String avatar;
                                private int tel_cand;
                                private String mail;
                                private String password;*/

                                cdt.setCandidatId(Integer.parseInt(obj.getString(EndPoints.KEY_ID_CAND)));
                                cdt.setPrenom_cand(obj.getString(EndPoints.KEY_PRENAME_CAND));
                                cdt.setAdresse_cand(obj.getString(KEY_ADRESSE_CAND));
                                cdt.setCode_postale(Integer.parseInt(obj.getString(KEY_CODE_POSTAL_CAND)));
                                cdt.setAvatar(obj.getString(KEY_AVATAR_CAND));
                                cdt.setTel_cand(Integer.parseInt(obj.getString(KEY_TEL_CAND)));
                                cdt.setMail(obj.getString(KEY_EMAIL_CAND));
                                cdt.setPassword((obj.getString(KEY_PWD_CAND)));


                                candidatsList.add(cdt);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(billionaireReq);


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        CheckNetwork checkNetwork = new CheckNetwork();
        if (checkNetwork.isConnected(getActivity())) {
        } else {
            //Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            //  NoNetwork homefragment = NoNetwork.newInstance();
            // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, homefragment).commit();

        }

       /* final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("activé wifi?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {

                        Thread background = new Thread() {
                            public void run() {

                                try {
                                    // Thread will sleep for 3 seconds
                                    sleep(2*1000);

                                //    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                  //  ft.detach().attach(this).commit();



                                } catch (Exception e) {

                                }
                            }
                        };

                        // start thread
                        background.start();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();

*/


    }

    public static AdminAllCandidatFragment newInstance() {
        if (fragment == null) {

            fragment = new AdminAllCandidatFragment();
        }
        return fragment;
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