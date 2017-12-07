package com.tn.isamm.jobplanet.candidant;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tn.isamm.jobplanet.R;
import com.tn.isamm.jobplanet.SessionManager;
import com.tn.isamm.jobplanet.model.ExperienceModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Rihab on 07/12/2017.
 */
public class CustomListExpAdapter extends BaseAdapter {

    SessionManager manager;
    double distanceSep;


    private Context activity;
    private LayoutInflater inflater;
    private List<ExperienceModel> soptItem;

    public CustomListExpAdapter(Activity activity, List<ExperienceModel> items) {
        this.activity = activity;
        this.soptItem = new ArrayList<ExperienceModel>();


        this.soptItem.addAll(items);


    }

    @Override
    public int getCount() {
        return soptItem.size();
    }

    @Override
    public Object getItem(int location) {
        return soptItem.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        manager = new SessionManager();


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_list_exp, null);


        TextView tv_ville = (TextView) convertView.findViewById(R.id.item_list_ville);
        TextView tv_adresse = (TextView) convertView.findViewById(R.id.item_list_adr);
        TextView tv_distance = (TextView) convertView.findViewById(R.id.item_list_distance);
        TextView tv_dis = (TextView) convertView.findViewById(R.id.item_list_dis);


        // getting billionaires data for the row
        final ExperienceModel m = soptItem.get(position);

        tv_ville.setText(m.getDae_finEx());
        tv_adresse.setText(m.getDate_debuEx());


     /*   img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete
                //update

            }
        });*/


        return convertView;
    }

}
