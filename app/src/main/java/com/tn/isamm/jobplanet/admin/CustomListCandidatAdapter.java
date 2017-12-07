package com.tn.isamm.jobplanet.admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tn.isamm.jobplanet.DetailList;
import com.tn.isamm.jobplanet.R;
import com.tn.isamm.jobplanet.candidant.EndPoints;
import com.tn.isamm.jobplanet.model.CandidatModel;
import com.tn.isamm.jobplanet.model.EntrepriseModel;
import com.tn.isamm.jobplanet.utils.AppController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


public class CustomListCandidatAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<CandidatModel> entrepriseItems;
    public static ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListCandidatAdapter(Activity activity, List<CandidatModel> entrepriseItems) {
        this.activity = activity;
        this.entrepriseItems = entrepriseItems;
    }

    @Override
    public int getCount() {
        return entrepriseItems.size();
    }

    @Override
    public Object getItem(int location) {
        return entrepriseItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_list_candidat, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.id_cand_thumbnail);
        TextView tv_titre = (TextView) convertView.findViewById(R.id.item_cand_nom_prenom);
        TextView tv_description = (TextView) convertView.findViewById(R.id.item_cand_adr);
        TextView tv_lieu = (TextView) convertView.findViewById(R.id.item_cand_tel);
        TextView tv_date = (TextView) convertView.findViewById(R.id.item_cand_mail);
        Button btn_supp = (Button) convertView.findViewById(R.id.item_cand_supp);

        final CandidatModel m = entrepriseItems.get(position);

        btn_supp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //    (int candidatId, String nom_cand, String prenom_cand, String adresse_cand, int code_postale,
                // String avatar, int tel_cand, String mail, String password)


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                     /*   CandidatModel cdt = new CandidatModel(
                                m.getCandidatId(),
                                m.getNom_cand(),
                                Integer.parseInt(input_tel.getText().toString()),
                                input_mail.getText().toString(),
                                Integer.parseInt(input_cin.getText().toString()),
                                input_emb.getText().toString(),
                                input_deb.getText().toString(),
                                spinner_grade.getSelectedItem().toString(),
                                spinner_TC.getSelectedItem().toString()
                        );
                        personnel.setId(1);
                        String message = new Genson().serialize(personnel);
                        //Log.i("SAIDDEBUG", "" + personnel);
*/
                        HttpURLConnection urlConnection = null;
                        try {
                            // URL url = new URL(EndPoints.URL_UPDATE_CAND);
                            //  urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setRequestMethod("PUT");
                            urlConnection.setDoOutput(true);
                            urlConnection.setRequestProperty("Content-Type", "application/json");

                            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                            //  out.write(message.getBytes());
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
                //Toast.makeText(, "Les donners sont modifier avec sucee ", Toast.LENGTH_LONG).show();


            }
        });


        // thumbnail image
        thumbNail.setImageUrl(m.getAdresse_cand(), imageLoader);
        tv_titre.setText(m.getAdresse_cand());
        tv_description.setText(String.valueOf(m.getAdresse_cand()));
        tv_lieu.setText(String.valueOf(m.getCode_postale()));
        tv_date.setText(String.valueOf(m.getMail()));

        // Listen for ListView Item Click
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(activity, DetailList.class);
                // Pass all data rank
                intent.putExtra("titre",
                        (m.getAdresse_cand()));
                // Pass all data country
                intent.putExtra("date",
                        (m.getAdresse_cand()));
                // Pass all data population
                intent.putExtra("description",
                        (m.getAdresse_cand()));
                // Pass all data flag
                intent.putExtra("image",
                        (m.getAvatar()));
                // Start SingleItemView Class*/
                activity.startActivity(intent);

            }
        });


        return convertView;
    }

}
