package com.example.marci.hf1;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.marci.hf1.R;
import com.example.marci.hf1.SignupActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;
    @Bind(R.id.saltar) TextView _saltar;
    String mail, password;
    public String nomfinal,imagenfinal;
    public int idfinal;
    private ProgressDialog pDialog;
    public Boolean correcto=false;
    TextView hazlofacil, hazlodesc;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Track.ttf");
        hazlofacil=findViewById(R.id.hazlofacilTV);
        hazlodesc=findViewById(R.id.hazlodesc);
        hazlofacil.setTypeface(face);
        hazlodesc.setTypeface(face);
        this.setTitle("");
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        _saltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });
    }



    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        mail = _emailText.getText().toString();
        password = _passwordText.getText().toString();
        // TODO: Implement your own authentication logic here.
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Autenticando...");
        pDialog.setCancelable(false);

        String url1="http://ksfactory.com.ve/cercanos.php?usuario=&mail="+mail;
        String url2="&pass="+password;
        final String urlfinal=url1+url2;
        makeJsonArrayRequest(urlfinal);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        if(correcto=true){
                        onLoginSuccess();} else onLoginFailed();

                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
      //  Toast.makeText(this, "Bienvenido "+nomfinal, Toast.LENGTH_SHORT).show();
        Intent vete= new Intent(getApplicationContext(),MainActivity.class);
        vete.putExtra("nombre",nomfinal);
        vete.putExtra("id",idfinal);
        vete.putExtra("imagen",imagenfinal);
        startActivity(vete);
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    private void makeJsonArrayRequest(String urljsonArray) {
        showpDialog();
        System.out.println(urljsonArray);
        JsonArrayRequest req = new JsonArrayRequest(urljsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        try {
                    // Parsing json object response
                    // response will be a json object
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject person = (JSONObject) response
                                        .get(i);
                                String nombre = person.getString("nombre");
                                String imagen = person.getString("imagen");
                                int id = person.getInt("id");
                                correcto = true;
                                nomfinal = nombre;
                                imagenfinal = imagen;
                                idfinal = id;
                            }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    System.out.println(e.getMessage());
                    correcto=false;
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(error.getMessage());
                correcto=false;
                // hide the progress dialog
                hidepDialog();
            }
        });
        // Adding request to request queue
        req.setShouldCache(false);
        Volley.newRequestQueue(getApplicationContext()).add(req);
    }




}