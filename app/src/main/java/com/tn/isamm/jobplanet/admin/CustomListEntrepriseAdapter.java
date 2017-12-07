package com.tn.isamm.jobplanet.admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tn.isamm.jobplanet.DetailList;
import com.tn.isamm.jobplanet.model.EntrepriseModel;
import com.tn.isamm.jobplanet.R;
import com.tn.isamm.jobplanet.utils.AppController;

import java.util.List;


public class CustomListEntrepriseAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<EntrepriseModel> entrepriseItems;
    public static ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListEntrepriseAdapter(Activity activity, List<EntrepriseModel> entrepriseItems) {
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
            convertView = inflater.inflate(R.layout.item_list_entreprise, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView tv_titre = (TextView) convertView.findViewById(R.id.item_titre);
        TextView tv_description = (TextView) convertView.findViewById(R.id.item_description);
        TextView tv_lieu = (TextView) convertView.findViewById(R.id.item_lieu);
        TextView tv_date = (TextView) convertView.findViewById(R.id.item_date);

        final EntrepriseModel m = entrepriseItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getLogo(), imageLoader);
        tv_titre.setText(m.getSiteweb());
        tv_description.setText(String.valueOf(m.getFondation()));
        tv_lieu.setText(String.valueOf(m.getSiege()));
        tv_date.setText(String.valueOf(m.getFondation()));

        // Listen for ListView Item Click
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(activity, DetailList.class);
                // Pass all data rank
                intent.putExtra("titre",
                        (m.getSiteweb()));
                // Pass all data country
                intent.putExtra("date",
                        (m.getFondation()));
                // Pass all data population
                intent.putExtra("description",
                        (m.getFondation()));
                // Pass all data flag
                intent.putExtra("image",
                        (m.getLogo()));
                // Start SingleItemView Class*/
                activity.startActivity(intent);

            }
        });


        return convertView;
    }

}
