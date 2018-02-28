package com.example.marci.hf1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class valoraciones extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private String jsonResponse;
    String urlcasi="http://www.ksfactory.com.ve/cercanos.php?pub_val=&id=";
    String url;
    LinearLayout raizvaloraciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoraciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int id=getIntent().getExtras().getInt("iduser");
        url=urlcasi+id;
/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/




        int iduser=getIntent().getExtras().getInt("iduser");

        Toast.makeText(this, ""+iduser, Toast.LENGTH_SHORT).show();

        raizvaloraciones= findViewById(R.id.raizValoraciones);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Obteniendo resultados...");
        pDialog.setCancelable(false);
        //  Toast.makeText(this, "elid: "+iduser, Toast.LENGTH_SHORT).show();
        makeJsonArrayRequest(url);


    }

    //dialog
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    private void makeJsonArrayRequest(String url) {

        showpDialog();

        // Toast.makeText(getContext(), ""+urlfinal, Toast.LENGTH_LONG).show();
        final JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());


                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "yes";
                            // Toast.makeText(MainActivity.this, "entro", Toast.LENGTH_SHORT).show();
                            //hidepDialog();

                            //     System.out.println(jsonResponse);
                            final ArrayList<String> idservidores=new ArrayList<>();


                          //capturo los datos de pefil


                                for (int i = 0; i < response.length(); i++) {

                                    JSONObject person = (JSONObject) response
                                            .get(i);

                                    String desc = person.getString("habilidad");

                                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                                    View inflatedLayout = inflater.inflate(R.layout.valoracion_item,null,false);
                                    TextView textView=inflatedLayout.findViewById(R.id.NombrePub);
                                    TextView categoria=inflatedLayout.findViewById(R.id.catPub);
                                    ImageView imageView=inflatedLayout.findViewById(R.id.ivPub);
                                    RatingBar ratingBar=inflatedLayout.findViewById(R.id.ratingBarPub);


                                    textView.setText(person.getString("habilidad"));
                                    categoria.setText(person.getString("cat"));
                                    Glide.with(getApplicationContext()).load
                                            (person.getString("imagen")).centerCrop()
                                            .into(imageView);
                                    ratingBar.setRating(Float.parseFloat(person.
                                            getString("reputacion")));


                                    inflatedLayout.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast.makeText(valoraciones.this, "goto" +
                                                    " JSON idservicio?", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                    raizvaloraciones.addView(inflatedLayout);


                                }


/*
                                raizhabilidades.removeAllViews();
                            for (int i=0; i<habilidad.size(); i++){
                                textView.setText(habilidad.get(i));
                                raizhabilidades.addView(textView);
                            }*/




                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hidepDialog();
            }
        });

        req.setShouldCache(false);
        Volley.newRequestQueue(getApplicationContext()).add(req);
    }


}
