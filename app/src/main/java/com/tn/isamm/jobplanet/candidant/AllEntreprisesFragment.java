package com.tn.isamm.jobplanet.candidant;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.tn.isamm.jobplanet.utils.AppController;
import com.tn.isamm.jobplanet.admin.CustomListEntrepriseAdapter;
import com.tn.isamm.jobplanet.model.EntrepriseModel;
import com.tn.isamm.jobplanet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AllEntreprisesFragment extends android.support.v4.app.Fragment {


    // Log tag
    private static final String TAG = AllEntreprisesFragment.class.getSimpleName();

    // Entreprise json url
    private static final String url = "http://192.168.1.5:8080/url/all-Entreprises";


    private ProgressDialog pDialog;
    private List<EntrepriseModel> entrepriseList = new ArrayList<EntrepriseModel>();
    private ListView listView;
    private CustomListEntrepriseAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static AllEntreprisesFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_entreprises, container, false);

        fragment = this;
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setShowHideAnimationEnabled(false);


        listView = (ListView) rootView.findViewById(R.id.list);
        adapter = new CustomListEntrepriseAdapter(getActivity(), entrepriseList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage(getResources().getString(R.string.tv_chargement));
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest entrepriseReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        entrepriseList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                EntrepriseModel entreprise = new EntrepriseModel();
                                entreprise.setSiteweb(obj.getString("siteweb"));
                                entreprise.setLogo(obj.getString("logo"));
                                entreprise.setSiteweb(obj.getString("descreptionEtr"));
                                entreprise.setSiege(obj.getString("siege"));
                                entreprise.setFondation(obj.getString("fondation"));
                                entrepriseList.add(entreprise);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
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
        AppController.getInstance().addToRequestQueue(entrepriseReq);


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

       /* CheckNetwork checkNetwork = new CheckNetwork();
        if (checkNetwork.isConnected(getActivity()) ) {
        } else {
            //Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            NoNetwork homefragment = NoNetwork.newInstance();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, homefragment).commit();

        }
*/
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

    public static AllEntreprisesFragment newInstance() {
        if (fragment == null) {

            fragment = new AllEntreprisesFragment();
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