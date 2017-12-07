package com.tn.isamm.jobplanet.candidant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tn.isamm.jobplanet.R;

public class MainActivityCandidat extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static int selectedPosition = 0; // 1 : consulter offre


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidat_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        selectFragment(0);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.candidat_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profil) {
            selectFragment(0);

        } else if (id == R.id.nav_Offres) {
            selectFragment(1);

        } else if (id == R.id.nav_recherche) {
            selectFragment(2);

        } else if (id == R.id.nav_deposez_cv) {
            selectFragment(3);

        } else if (id == R.id.nav_profils_entreprises) {
            selectFragment(4);

        } else if (id == R.id.nav_saves) {
            selectFragment(5);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void selectFragment(int position) {

        selectedPosition = position;
        Fragment fragment = null;
        switch (position) {
            case 0:
                //profil
                CandidatProfilFragment profilfragment = CandidatProfilFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.candidat_main_fragment, profilfragment).commit();
                break;

            case 1:
                // offres
                AllOffresFragment offrefragment = AllOffresFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.candidat_main_fragment, offrefragment).commit();
                break;

            case 2:
                //Recherhes
                CandidatRechercheFragment recherchefragment = CandidatRechercheFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.candidat_main_fragment, recherchefragment).commit();
                break;

            case 3:
                //deposez cv
                DeposeCVFragment deposecvfragment = DeposeCVFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.candidat_main_fragment, deposecvfragment).commit();
                break;

            case 4:
                //profil entreprises
                AllEntreprisesFragment allentreprisesfragment = AllEntreprisesFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.candidat_main_fragment, allentreprisesfragment).commit();
                break;

            case 5:
                //enregistremens
                SavedFragment mysavedfragment = SavedFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.candidat_main_fragment, mysavedfragment).commit();
                break;


        }
    }
}
