package com.tn.isamm.jobplanet.recruteur;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.owlike.genson.Genson;
import com.tn.isamm.jobplanet.R;
import com.tn.isamm.jobplanet.SessionManager;
import com.tn.isamm.jobplanet.activities.LoginActivity;
import com.tn.isamm.jobplanet.candidant.EndPoints;
import com.tn.isamm.jobplanet.model.CandidatModel;
import com.tn.isamm.jobplanet.utils.CheckNetwork;
import com.tn.isamm.jobplanet.utils.ValidateUserInfo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RegistrerRecruteur extends AppCompatActivity implements View.OnClickListener {

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    SessionManager manager;

    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextTelephone;
    private EditText editTextPassword;
    private EditText editTextprenom;
    private EditText editTextadrsCand;
    private EditText editTextavatar;
    private EditText editTextcodepostal;
    private EditText editTextRePassword;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonRegister;

    private ProgressDialog pDialog;


    ImageView txt_alreadyHave;
    Button btn_registrar;


    private CreateUserTask mCreateTask = null;


    public String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer_candidat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        manager = new SessionManager();

        editTextUsername = (EditText) findViewById(R.id.register_nom_cand);
        editTextprenom = (EditText) findViewById(R.id.register_prenom_cand);
        editTextEmail = (EditText) findViewById(R.id.register_mail);
        editTextTelephone = (EditText) findViewById(R.id.register_tel_cand);
        editTextadrsCand = (EditText) findViewById(R.id.register_adresse_cand);
        editTextavatar = (EditText) findViewById(R.id.register_avatar);
        editTextcodepostal = (EditText) findViewById(R.id.register_code_postale);
        editTextPassword = (EditText) findViewById(R.id.register_password);
        editTextRePassword = (EditText) findViewById(R.id.register_repassword);


        // imageView  = (ImageView) findViewById(R.id.register_avatar);


        buttonRegister = (Button) findViewById(R.id.register_submit);

        buttonRegister.setOnClickListener(this);
        // imageView.setOnClickListener(this);


        // txt_alreadyHave = (ImageView) findViewById(R.id.imv_backtologin);
        //txt_alreadyHave.setOnClickListener(this);

        //btn_registrar = (Button) findViewById(R.id.btn_register);
        //btn_registrar.setOnClickListener(this);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptCreate() {
        // Store values at the time of the login attempt.


        final String nameUser = editTextUsername.getText().toString().trim();
        final String prenom = editTextprenom.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String telephone = editTextTelephone.getText().toString().trim();
        final String adresse = editTextadrsCand.getText().toString().trim();
        final String avatar = editTextavatar.getText().toString().trim();
        final String potal = editTextcodepostal.getText().toString().trim();
        final String rePassword = editTextRePassword.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();


        //Converting Bitmap to String

        //bitmap = ((BitmapDrawable)imageView;
        //Bitmap img =  imageView.getBitmap();

        //  imageView.buildDrawingCache();
        // Bitmap bmap = imageView.getDrawingCache();


        //  final String image = getStringImage(bmap);


        boolean cancel = false;
        View focusView = null;

        ValidateUserInfo validate = new ValidateUserInfo();

        // Check for a valid email address.
        if (TextUtils.isEmpty(nameUser)) {
            editTextUsername.setError("Champs obligatoire");
            focusView = editTextUsername;
            cancel = true;
        } else if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Champs obligatoire");
            focusView = editTextEmail;
            cancel = true;
        } else if (!validate.isEmailValid(email)) {
            editTextEmail.setError("mail invalid");
            focusView = editTextEmail;
            cancel = true;
        } else if (TextUtils.isEmpty(telephone)) {
            editTextTelephone.setError("Champs obligatoire");
            focusView = editTextTelephone;
            cancel = true;
        } else if (telephone.length() < 8) {
            editTextTelephone.setError("Numéro invalide");
            focusView = editTextTelephone;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Champs obligatoire");
            focusView = editTextPassword;
            cancel = true;
        } else if (!validate.isPasswordValid(password)) {
            editTextPassword.setError(getString(R.string.error_invalid_password));
            focusView = editTextPassword;
            cancel = true;
        } else if (!validate.isPasswordValid(rePassword)) {
            editTextRePassword.setError(getString(R.string.error_invalid_password));
            focusView = editTextRePassword;
            cancel = true;
        } else if (!password.equals(rePassword)) {
            editTextRePassword.setError("Mot de passe non identique");
            focusView = editTextRePassword;
            cancel = true;

        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //TODO Create account logic
            // Show a progress spinner, and kick off a background task to
            // perform the user registration attempt.
            // pDialog = new ProgressDialog(RegistrerCandidat.this);
            // Showing progress dialog before making http request
            // pDialog.setMessage(getResources().getString(R.string.tv_chargement));
            //  pDialog.show();


            mCreateTask = new CreateUserTask(nameUser, prenom, adresse, avatar, potal, email, password, telephone);
            mCreateTask.execute((Void) null);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_submit:
                attemptCreate();
                break;
            //   case R.id.imv_backtologin:
            //     startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            //    finish();
            //   break;
            // case R.id.register_im_photo:
            // showFileChooser();
            //   break;

        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class CreateUserTask extends AsyncTask<Void, Void, Boolean> {
        private final String mName;
        private final String mPrenom;
        private final String mEmail;
        private final String mPassword;
        private final String mTel;
        private final String mAdresse;
        private final String mAvatar;
        private final String mPotal;


        // nameUser, password, email, telephone , adresse, avatar ,potal ,rePassword , Password


        //  private final String mImage;

        CreateUserTask(String name, String prenom, String adresse, String avatar, String postal, String email, String password, String tel) {

            mName = name;
            mPrenom = prenom;
            mEmail = email;
            mPassword = password;
            mTel = tel;
            mAdresse = adresse;
            mAvatar = avatar;
            mPotal = postal;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: check if account already exists against a network service.

            // File file = new File(mStrFile);


            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            // TODO: if there's no account registered, register the new account here.
            //Showing the progress dialog

            new Thread(new Runnable() {
                @Override
                public void run() {


                    //constructor(public candidatId: string, public nom_cand: string, public prenom_cand: string,
                    // public adresse_cand: string, public code_postale: Number, public avatar: string,
                    // public tel_cand: Number,
                    // public mail: string, public password: string) {


                    //let candidat= new Candidat (null, nom_cand, prenom_cand, adresse_cand,
                    // code_postale,avatar, tel_cand, mail, password);


                  /*  editTextUsername;
                    private EditText editTextEmail;
                    private EditText editTextTelephone;
                    private EditText editTextPassword;
                    private EditText editTextprenom ;
                    private EditText editTextadrsCand;
                    private EditText editTextavatar ;
                    private EditText editTextcodepostal ;
                    private EditText editTextRePassword;*/

                    CandidatModel cdt = new CandidatModel(
                            editTextUsername.getText().toString(),
                            editTextprenom.getText().toString(),
                            editTextadrsCand.getText().toString(),
                            Integer.parseInt(editTextcodepostal.getText().toString()),
                            editTextavatar.getText().toString(),
                            Integer.parseInt(editTextTelephone.getText().toString()),
                            editTextEmail.getText().toString(),
                            editTextPassword.getText().toString()
                    );

                    String message = new Genson().serialize(cdt);
                    Log.i("BUGCDT", "" + cdt);

                    HttpURLConnection urlConnection = null;
                    try {
                        URL url = new URL(EndPoints.REGISTER_URL);
                        urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("POST");
                        urlConnection.setDoOutput(true);
                        urlConnection.setRequestProperty("Content-Type", "application/json");

                        OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                        out.write(message.getBytes());
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


            Toast.makeText(RegistrerRecruteur.this, "Account created", Toast.LENGTH_SHORT).show();


            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mCreateTask = null;
            CheckNetwork checkNetwork = new CheckNetwork();
            if (checkNetwork.isConnected(RegistrerRecruteur.this) && success) {
                Toast.makeText(RegistrerRecruteur.this, "Account created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegistrerRecruteur.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mCreateTask = null;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegistrerRecruteur.this, LoginActivity.class));
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MainActivity", "onPause");
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
