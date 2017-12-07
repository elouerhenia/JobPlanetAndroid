package com.tn.isamm.jobplanet;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by odc on 25/08/2016.
 */
public class CustomListCGCAdapter extends BaseAdapter {

    SessionManager manager;
    double distanceSep;


    private Context activity;
    private LayoutInflater inflater;
    private List<Spot> soptItem;

    public CustomListCGCAdapter(Activity activity, List<Spot> billionairesItems) {
        this.activity = activity;
//        this.billionairesItems.clear();


        this.soptItem = new ArrayList<Spot>();


        this.soptItem.addAll(billionairesItems);


        // this.soptItem = billionairesItems;
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
            convertView = inflater.inflate(R.layout.list_row_cgc, null);


        TextView tv_ville = (TextView) convertView.findViewById(R.id.item_list_ville);
        TextView tv_adresse = (TextView) convertView.findViewById(R.id.item_list_adr);
        TextView tv_distance = (TextView) convertView.findViewById(R.id.item_list_distance);
        TextView tv_dis = (TextView) convertView.findViewById(R.id.item_list_dis);


        // getting billionaires data for the row
        final Spot m = soptItem.get(position);

        tv_ville.setText(m.getVille());
        tv_adresse.setText(m.getAdress());


        Log.e("position spot", m.getLat() + ", " + m.getLn());
        Log.e("distance", "" + m.getDistance());


        //etat.setImageResource(R.drawable.eb_item_vert);


        return convertView;
    }

}
