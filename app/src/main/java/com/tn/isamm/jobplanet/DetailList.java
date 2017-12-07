package com.tn.isamm.jobplanet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.tn.isamm.jobplanet.admin.CustomListEntrepriseAdapter;


/**
 * Created by odc on 18/08/2016.
 */
public class DetailList extends Activity {

    TextView n, a, c, m;
    String titre, date, description, image;
    // ImageLoader imageLoader = new ImageLoader(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_entreprise);
        // declaration

/*
        ImageView im = (ImageView) findViewById(R.id.retour_fromdetail);
        im.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();


                //   FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                // Fragment newFragment = new Fragment1();
                //transaction.add(R.id.content_frame, newFragment);
                //transaction.addToBackStack(null);
                //transaction.commit();



            }
        });*/


        Intent i = getIntent();
        // Get the result of rank
        titre = i.getStringExtra("titre");
        // Get the result of country
        date = i.getStringExtra("date");
        // Get the result of population
        description = i.getStringExtra("description");

        image = i.getStringExtra("image");


        // Locate the TextViews in singleitemview.xml
        n = (TextView) findViewById(R.id.item_titre);
        a = (TextView) findViewById(R.id.item_date);
        c = (TextView) findViewById(R.id.item_description);

        // Locate the ImageView in singleitemview.xml
        // ImageView imgflag = (ImageView) findViewById(R.id.thumbnail);

        // Set results to the TextViews

        n.setText(titre);
        a.setText(date);
        c.setText(description);


        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.thumbnail);

        // thumbnail image
        thumbNail.setImageUrl(image, CustomListEntrepriseAdapter.imageLoader);


    }


    @Override
    public void onBackPressed() {
        //startActivity(new Intent(DetailList.this, MainActivity.class));
        finish();
    }


}


