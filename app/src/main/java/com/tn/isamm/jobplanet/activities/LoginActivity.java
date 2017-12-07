package com.tn.isamm.jobplanet.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tn.isamm.jobplanet.admin.MainActivityAdmin;
import com.tn.isamm.jobplanet.candidant.EndPoints;
import com.tn.isamm.jobplanet.candidant.RegistrerCandidat;
import com.tn.isamm.jobplanet.recruteur.MainActivityRecruteur;
import com.tn.isamm.jobplanet.R;
import com.tn.isamm.jobplanet.SessionManager;
import com.tn.isamm.jobplanet.candidant.MainActivityCandidat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    // Keep track of the login task to ensure we can cancel it if requested.
    private RecruteurLoginTask mAuthTaskRec = null;
    private CandidatLoginTask mAuthTaskCand = null;


    private AutoCompleteTextView mUserNameView;
    private TextInputLayout input_user_name, input_password;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button login_button, gotoregister;
    SessionManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        manager = new SessionManager();


        initView();
    }

    public void initView() {
        mLoginFormView = findViewById(R.id.form_login);
        mProgressView = findViewById(R.id.progress_login);
        mUserNameView = (AutoCompleteTextView) findViewById(R.id.tv_user_name);
        mPasswordView = (EditText) findViewById(R.id.tv_password);
        input_user_name = (TextInputLayout) findViewById(R.id.input_user_name);
        input_password = (TextInputLayout) findViewById(R.id.input_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        login_button = (Button) findViewById(R.id.btn_login);
        login_button.setOnClickListener(this);

        Button forgot_password = (Button) findViewById(R.id.btn_forgot_password);
        forgot_password.setOnClickListener(this);
        Button register = (Button) findViewById(R.id.btn_forgot_register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                attemptLogin();
                break;

            case R.id.btn_forgot_register:
                Intent itent = new Intent(LoginActivity.this, RegistrerCandidat.class);
                startActivity(itent);
                break;

            case R.id.btn_forgot_password:
                Snackbar.make(v, getString(R.string.snackbar_forgot_password), Snackbar.LENGTH_LONG)
                        .setAction("^_^", null).show();
                break;


        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form. If there are form errors
     * (invalid email, missing fields, etc.), the errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if ((mAuthTaskRec != null) || (mAuthTaskCand != null)) {
            return;
        }

        // Reset errors.
        input_user_name.setError(null);
        input_password.setError(null);

        String userName = mUserNameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(userName)) {
            input_user_name.setError(getString(R.string.error_no_name));
            focusView = mUserNameView;
            cancel = true;
        } else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            input_password.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            hideInput(login_button);
            showProgress(true);


            String compte = manager.getPreferences(LoginActivity.this, "compte");
            if (compte.equals("admin")) {
                if ((userName.equals("admin@rihab.com")) && (password.equals("admin"))) {
                    Intent itent = new Intent(LoginActivity.this, MainActivityAdmin.class);
                    startActivity(itent);

                }

            } else if (compte.equals("recruteur")) {
                mAuthTaskRec = new RecruteurLoginTask(userName, password);
                mAuthTaskRec.execute((Void) null);


            } else if (compte.equals("candidat")) {
                mAuthTaskCand = new CandidatLoginTask(userName, password);
                mAuthTaskCand.execute((Void) null);

            }

        }
    }


    private boolean isEmailValid(String userName) {
        return userName.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 4 && password.length() <= 20;
    }

    public void hideInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }


    private class RecruteurLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mPhone;
        private final String mPassword;

        RecruteurLoginTask(String userName, String password) {
            mPhone = userName;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mPhone)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.

            StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_AUTH_REC,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            //Disimissing the progress dialog
                            // hidePDialog();


                            JSONObject root = null;
                            try {
                                root = new JSONObject(response);


                                Log.i("myid", root.getString("id_etr"));

                               /* public static final String KEY_ID_ETR = "id_etr";
                                public static final String KEY_NOM_ETR = "nom_ent" ;
                                public static final String KEY_POPULARITE_ETR = "popularite" ;
                                public static final String KEY_SIEGE_ETR = "siege" ;
                                public static final String KEY_LOGO_ETR = "logo" ;
                                public static final String KEY_SITE_ETR = "siteweb" ;
                                public static final String KEY_EFFECTIF_ETR = "effectif" ;
                                public static final String KEY_FONDATION_ETR = "fondation" ;
                                public static final String KEY_SECTEUR_ETR =  "secteur_etr" ;
                                public static final String KEY_DESCRIPTION_ETR = "descreption_etr" ;
                                public static final String KEY_EMAIL_ETR = "email_etr" ;
                                public static final String KEY_LOGIN_ETR = "login" ;
                                public static final String KEY_PWD_ETR = "password" ;
                                public static final String KEY_ETAT_COMPTE_ETR = "etat_compt_etr";*/

                                manager.setPreferences(LoginActivity.this, "id_connected", root.getString(EndPoints.KEY_ID_ETR));
                               /* manager.setPreferences(LoginActivity.this, "nom", root.getString(EndPoints.KEY_ID_CAND));
                                manager.setPreferences(LoginActivity.this, "email", root.getString(EndPoints.KEY_ID_CAND));
                                manager.setPreferences(LoginActivity.this, "type", root.getString(EndPoints.KEY_ID_CAND));
                                manager.setPreferences(LoginActivity.this, "id", root.getString(EndPoints.KEY_ID_CAND));
                                manager.setPreferences(LoginActivity.this, "nom", root.getString(EndPoints.KEY_ID_CAND));
                                manager.setPreferences(LoginActivity.this, "email", root.getString(EndPoints.KEY_ID_CAND));
                                manager.setPreferences(LoginActivity.this, "type", root.getString(EndPoints.KEY_ID_CAND));*/
                                manager.setPreferences(LoginActivity.this, "status", "1");


                                Intent main = new Intent(LoginActivity.this, MainActivityRecruteur.class);

                                startActivity(main);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //hidePDialog();

                            Toast.makeText(LoginActivity.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    // params.put(KEY_IMAGE, mImage);
                    params.put("mail", mPhone);
                    params.put("pass", mPassword);
                    return params;
                }


            };

            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(stringRequest);


            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTaskRec = null;
            showProgress(false);

            if (success) {
                String compte = manager.getPreferences(LoginActivity.this, "compte");
                if (compte.equals("admin")) {
                    Intent itent = new Intent(LoginActivity.this, MainActivityAdmin.class);
                    startActivity(itent);
                } else if (compte.equals("recruteur")) {
                    Intent itent = new Intent(LoginActivity.this, MainActivityRecruteur.class);
                    startActivity(itent);
                } else if (compte.equals("candidat")) {
                    Intent itent = new Intent(LoginActivity.this, MainActivityCandidat.class);
                    startActivity(itent);
                }

            } else {
                input_password.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTaskRec = null;
            showProgress(false);
        }
    }

    private class CandidatLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mPhone;
        private final String mPassword;

        CandidatLoginTask(String userName, String password) {
            mPhone = userName;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.URL_AUTH_CAND,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            //Disimissing the progress dialog
                            // hidePDialog();


                            JSONObject root = null;
                            try {
                                root = new JSONObject(response);


                                Log.i("myid", root.getString("candidatId"));

                                manager.setPreferences(LoginActivity.this, "id", root.getString("candidatId"));
                                manager.setPreferences(LoginActivity.this, "nom", root.getString("nom_cand"));
                                manager.setPreferences(LoginActivity.this, "prenom", root.getString("prenom_cand"));
                                manager.setPreferences(LoginActivity.this, "adresse", root.getString("adresse_cand"));
                                manager.setPreferences(LoginActivity.this, "code", root.getString("code_postale"));
                                manager.setPreferences(LoginActivity.this, "avatar", root.getString("avatar"));
                                manager.setPreferences(LoginActivity.this, "tel", root.getString("tel_cand"));
                                manager.setPreferences(LoginActivity.this, "tel", root.getString("mail"));
                                manager.setPreferences(LoginActivity.this, "status", "1");


                                Intent main = new Intent(LoginActivity.this, MainActivityCandidat.class);

                                startActivity(main);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //hidePDialog();

                            Toast.makeText(LoginActivity.this, "Erreur de connexion", Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    // params.put(KEY_IMAGE, mImage);
                    params.put("mail", mPhone);
                    params.put("pass", mPassword);
                    return params;
                }


            };

            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(stringRequest);


            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTaskCand = null;
            showProgress(false);

            if (success) {
                String compte = manager.getPreferences(LoginActivity.this, "compte");
                if (compte.equals("admin")) {
                    Intent itent = new Intent(LoginActivity.this, MainActivityAdmin.class);
                    startActivity(itent);
                } else if (compte.equals("recruteur")) {
                    Intent itent = new Intent(LoginActivity.this, MainActivityRecruteur.class);
                    startActivity(itent);
                } else if (compte.equals("candidat")) {
                    Intent itent = new Intent(LoginActivity.this, MainActivityCandidat.class);
                    startActivity(itent);
                }

            } else {
                input_password.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTaskCand = null;
            showProgress(false);
        }
    }


}
