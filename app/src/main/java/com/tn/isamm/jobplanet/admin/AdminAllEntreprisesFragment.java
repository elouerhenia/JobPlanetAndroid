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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.tn.isamm.jobplanet.R;
import com.tn.isamm.jobplanet.candidant.EndPoints;
import com.tn.isamm.jobplanet.model.EntrepriseModel;
import com.tn.isamm.jobplanet.utils.AppController;
import com.tn.isamm.jobplanet.utils.CheckNetwork;

import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_DESCRIPTION_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_EFFECTIF_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_EMAIL_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_ETAT_COMPTE_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_FONDATION_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_LOGIN_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_LOGO_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_NOM_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_POPULARITE_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_PWD_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_SECTEUR_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_SIEGE_ETR;
import static com.tn.isamm.jobplanet.candidant.EndPoints.KEY_SITE_ETR;


public class AdminAllEntreprisesFragment extends android.support.v4.app.Fragment {


    // Log tag
    private static final String TAG = AdminAllEntreprisesFragment.class.getSimpleName();

    private ProgressDialog pDialog;
    private List<EntrepriseModel> entreprisesList = new ArrayList<EntrepriseModel>();
    private ListView listViewEntreprise;
    private CustomListEntrepriseAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static AdminAllEntreprisesFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admin_all_entreprise, container, false);

        fragment = this;

        //((AppCompatActivity) getActivity()).getSupportActionBar().show();
        //((AppCompatActivity) getActivity()).getSupportActionBar().setShowHideAnimationEnabled(false);

        listViewEntreprise = (ListView) rootView.findViewById(R.id.list);
        adapter = new CustomListEntrepriseAdapter(getActivity(), entreprisesList);
        listViewEntreprise.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage(getResources().getString(R.string.tv_chargement));
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest billionaireReq = new JsonArrayRequest(EndPoints.URL_GET_ALL_ENTREPRISES,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        entreprisesList.clear();
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                EntrepriseModel entreprise = new EntrepriseModel();

                               /* private int id_etr;
                                private String nom_ent;
                                private int popularite;
                                private String siege;
                                private String logo;
                                private String siteweb;
                                private int effectif;
                                private String fondation;
                                private String secteur_etr;
                                private String descreption_etr;
                                private String email_etr;
                                private String login;
                                private String password;
                                private int etat_compt_etr;*/

                                entreprise.setId_etr(Integer.parseInt(obj.getString(EndPoints.KEY_ID_ETR)));
                                entreprise.setNom_ent(obj.getString(KEY_NOM_ETR));
                                entreprise.setPopularite(Integer.parseInt(obj.getString(KEY_POPULARITE_ETR)));
                                entreprise.setSiege(obj.getString(KEY_SIEGE_ETR));
                                entreprise.setLogo(obj.getString(KEY_LOGO_ETR));
                                entreprise.setSiteweb(obj.getString(KEY_SITE_ETR));
                                entreprise.setEffectif(Integer.parseInt(obj.getString(KEY_EFFECTIF_ETR)));
                                entreprise.setFondation(obj.getString(KEY_FONDATION_ETR));
                                entreprise.setSecteur_etr(obj.getString(KEY_SECTEUR_ETR));
                                entreprise.setDescreption_etr(obj.getString(KEY_DESCRIPTION_ETR));
                                entreprise.setEmail_etr(obj.getString(KEY_EMAIL_ETR));
                                entreprise.setLogin(obj.getString(KEY_LOGIN_ETR));
                                entreprise.setPassword(obj.getString(KEY_PWD_ETR));
                                entreprise.setEtat_compt_etr(Integer.parseInt(obj.getString(KEY_ETAT_COMPTE_ETR)));

                                entreprisesList.add(entreprise);

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

    public static AdminAllEntreprisesFragment newInstance() {
        if (fragment == null) {

            fragment = new AdminAllEntreprisesFragment();
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