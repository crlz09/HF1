package com.example.marci.hf1;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class anuncio extends Fragment {

    private String urlJsonObj = "https://api.androidhive.info/volley/person_object.json";
    private String urlJsonArry = "http://www.ksfactory.com.ve/cercanos.php?cercat=&cat=";
    String urlanuncio="http://ksfactory.com.ve/cercanos.php?anuncios=&";
    LinearLayout anuncio,raiz,nuevoanuncio;
    TextView tvNombre, tvCat, tvFecha, tvDesc, tvVisitas, tvTitulo, tvCedula;
    private static String TAG = MainActivity.class.getSimpleName();

    private String jsonResponse;
    ArrayList<String> tlffinal;
    ArrayList<String> aTitulos,aDesc,aCat,aNombre,aFecha,aVisita;

    private ProgressDialog pDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_anuncio, container, false);
        raiz= vista.findViewById(R.id.anuncio_laypadre);
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Cargando últimos anuncios...");
        pDialog.setCancelable(false);
        makeJsonArrayRequest();



        return  vista;

    }

//DIALOG:------------------------------------------------------------------

    public void showDialog(final Activity activity, final String tit, final String msg, final String cate, final String num, final String fec, final String visit, final String nom){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);

        View view  = getActivity().getLayoutInflater().inflate(R.layout.anuncio_dialog, null);
        dialog.setContentView(view);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);


        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", num, null));
                startActivity(intent);
                dialog.dismiss();

            }

        });

        dialog.show();

        dialog.setCanceledOnTouchOutside(true);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void makeJsonObjectRequest() {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    String name = response.getString("name");


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        Volley.newRequestQueue(getContext()).add(jsonObjReq);
    }


    private void makeJsonArrayRequest() {

        showpDialog();
      String urlfinal=urlanuncio+"DESC";

        System.out.println(urlfinal);
        // Toast.makeText(getContext(), ""+urlfinal, Toast.LENGTH_LONG).show();
        JsonArrayRequest req = new JsonArrayRequest(urlfinal,
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


                            final ArrayList<String> tNombre = new ArrayList<>();
                            final ArrayList<String> tCat = new ArrayList<>();
                            final ArrayList<String> tTitulo = new ArrayList<>();
                            final ArrayList<String> tDesc = new ArrayList<>();
                            final ArrayList<String> tVisitas = new ArrayList<>();
                            final ArrayList<String> tFecha = new ArrayList<>();

                            final ArrayList<String> tlffinal=new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {

                                nuevoanuncio = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.anuncio_item, null);
                                LinearLayout base = nuevoanuncio.findViewById(R.id.anuncio_root);

                                JSONObject person = (JSONObject) response
                                        .get(i);

                                tvNombre = nuevoanuncio.findViewById(R.id.anuncioNombre);
                                tvCat = nuevoanuncio.findViewById(R.id.anuncioCat);
                                tvTitulo = nuevoanuncio.findViewById(R.id.anuncioTitulo);
                                tvDesc = nuevoanuncio.findViewById(R.id.anuncioDesc);
                                tvVisitas = tvCat = nuevoanuncio.findViewById(R.id.anuncioVisitas);
                                tvFecha = tvCat = nuevoanuncio.findViewById(R.id.anuncioFecha);

                                tTitulo.add(person.getString("titulo"));
                                tCat.add(person.getString("descripcion"));
                                tTitulo.add(person.getString("titulo"));
                                tDesc.add(person.getString("descripcion"));
                                tVisitas.add(person.getString("visitas") + " visitas");
                                tNombre.add(person.getString("responsable"));
                                tFecha.add(person.getString("fecha"));
                                tlffinal.add(person.getString("telefono"));

                                tvNombre.setText(tNombre.get(i));
                                tvCat.setText(tCat.get(i));
                                tvTitulo.setText(tTitulo.get(i));
                                tvDesc.setText(tDesc.get(i));
                                tvVisitas.setText(tVisitas.get(i));
                                tvFecha.setText(tFecha.get(i));



                                final int finalI = i;
                                nuevoanuncio.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      showDialog(getActivity(),tTitulo.get(finalI),
                                              tDesc.get(finalI),tCat.get(finalI),tlffinal.get(finalI).toString(),
                                              tFecha.get(finalI),tVisitas.get(finalI),tNombre.get(finalI));
                                  }
                              });

                                raiz.addView(nuevoanuncio);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hidepDialog();
            }
        });

        req.setShouldCache(false);
        Volley.newRequestQueue(getContext()).add(req);
    }

}