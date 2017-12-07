package com.tn.isamm.jobplanet.candidant;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tn.isamm.jobplanet.CustomListCGCAdapter;
import com.tn.isamm.jobplanet.R;
import com.tn.isamm.jobplanet.SessionManager;
import com.tn.isamm.jobplanet.Spot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AllOffresFragment extends android.support.v4.app.Fragment {


    // Log tag
    private static final String TAG = AllOffresFragment.class.getSimpleName();

    //private static final String url = DataHelpers.baseUrl+"ListeCGC.php";
    private ProgressDialog pDialog;
    private List<Spot> spotList = new ArrayList<Spot>();
    private ListView listView;
    private CustomListCGCAdapter adapter;


    SessionManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //manager = new SessionManager();
        // manager.setPreferences(getActivity(), "latitude",Double.toString(0));
        // manager.setPreferences(getActivity(), "battery",Double.toString(0));


    }

    View rootView;


    public static AllOffresFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = this;
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setShowHideAnimationEnabled(false);


        rootView = inflater.inflate(R.layout.fragment_liste_cgc, container, false);


        listView = (ListView) rootView.findViewById(R.id.list_cgc);


        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading");
        pDialog.show();


        // Creating volley request obj
       /* JsonArrayRequest spotReq = new JsonArrayRequest(EndPoints.URL_GET_TASK_BY_ID,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        spotList.clear();*/

        JSONArray tabJson = null;
        try {
            tabJson = new JSONArray(EndPoints.URL_GET_ALL_ENTREPRISES);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Parsing json
        for (int i = 0; i < tabJson.length(); i++) {
            try {

                JSONObject obj = tabJson.getJSONObject(i);
                Spot monSpot = new Spot();


                monSpot.setVille(obj.getString("popularite"));
                monSpot.setAdress(obj.getString("popularite"));
                monSpot.setLat(obj.getString("popularite"));
                monSpot.setLn(obj.getString("popularite"));

                spotList.add(monSpot);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        // notifying list adapter about data changes
        // so that it renders the list view with updated data

        adapter = new CustomListCGCAdapter(getActivity(), spotList);
        listView.setAdapter(adapter);


        adapter.notifyDataSetChanged();
               /*     }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });*/

        // Adding request to request queue
        //  AppController.getInstance().addToRequestQueue(spotReq);


        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

      /*  CheckNetwork checkNetwork = new CheckNetwork();
        if (checkNetwork.isConnected(getActivity()) ) {
        } else {
            //Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            NoNetwork homefragment = NoNetwork.newInstance();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, homefragment).commit();

        }*/

    }

    public static AllOffresFragment newInstance() {
        if (fragment == null) {

            fragment = new AllOffresFragment();
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