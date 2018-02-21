package com.example.marci.hf1;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class cercanos extends Fragment {

    // json object response url
    private String urlJsonObj = "https://api.androidhive.info/volley/person_object.json";
    // json array response url
    private String urlJsonArry = "http://www.ksfactory.com.ve/cercanos.php?cercanos=&";
    private static String TAG = MainActivity.class.getSimpleName();
    // Progress dialog
    private ProgressDialog pDialog;
    public  Double lon, lat;
    // temporary string to show the parsed response
    private String jsonResponse;
    String idservidor,cat,nombre,ciudad,estado,descripcion,precio,formapago,experiencia,zonas,reputacion,telefono,imag;
    Double latitud,longitud,distance;
    public static ArrayList<Integer> images;
    public static ArrayList<String> nombres,profesiones,ciudades,distances,cats;
    public GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.nuevocer, container, false);
        gridView = vista.findViewById(R.id.grid);
        return  vista;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        if (isVisibleToUser) {
            // load data here
           // Toast.makeText(getContext(), "Vista en cercanos USERVISIBLE", Toast.LENGTH_LONG).show();

            lon=MainActivity.lon;
            lat=MainActivity.lat;
            if (lat!=null){
                pDialog = new ProgressDialog(getContext());
                pDialog.setMessage("Filtrando cercanos...");
                pDialog.setCancelable(false);
                makeJsonArrayRequest();
                System.out.println(jsonResponse);
            } else { showDialog(getActivity());
            }

        }else{
            // fragment is no longer visible
        }
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
        String urlfinal= urlJsonArry+"lat="+lat+"&lng="+lon;
        System.out.println(urlfinal);
        JsonArrayRequest req = new JsonArrayRequest(urlfinal,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        try {
                            jsonResponse = "yes";
                            final ArrayList<String>idservidores=new ArrayList<>();
                            ArrayList<String> catspre=new ArrayList<>();
                            final ArrayList<String> nombrespre=new ArrayList<>();
                            final ArrayList<String> ciudadespre=new ArrayList<>();
                            final ArrayList<String> estados=new ArrayList<>();
                            final ArrayList<String> descripciones=new ArrayList<>();
                            final ArrayList<String> precios=new ArrayList<>();
                            final ArrayList<String> formapagos=new ArrayList<>();
                            final ArrayList<String> experiencias=new ArrayList<>();
                            final ArrayList<String> zonass=new ArrayList<>();
                            final ArrayList<String> telefonos=new ArrayList<>();
                            final ArrayList<Double> latitudes=new ArrayList<>();
                            final ArrayList<Double> longitudes=new ArrayList<>();
                            final ArrayList<Integer> imagespre =new ArrayList<>();
                            final ArrayList<String> profesionespre=new ArrayList<>();
                            final ArrayList<String> distancespre=new ArrayList<>();
                            final ArrayList<String> imagenes = new ArrayList<>();

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject person = (JSONObject) response
                                        .get(i);
                                idservidor = person.getString("idservidor");
                                cat = person.getString("cat");
                                nombre = person.getString("nombre");
                                ciudad = person.getString("ciudad");
                                estado = person.getString("estado");
                                descripcion = person.getString("descripcion");
                                precio = person.getString("precio");
                                formapago = person.getString("formapago");
                                experiencia = person.getString("experiencia");
                                zonas = person.getString("zonas");
                                reputacion = person.getString("reputacion");
                                telefono = person.getString("telefono");
                                latitud = person.getDouble("lat");
                                longitud = person.getDouble("lng");
                                distance = person.getDouble("distance");
                                imag = person.getString("imagen");
                                String distauxiliar= distance.toString().substring(0,3);

                                idservidores.add(idservidor);
                                catspre.add(cat);
                                nombrespre.add(nombre);
                                ciudadespre.add(ciudad);
                                estados.add(estado);
                                descripciones.add(descripcion);
                                precios.add(precio);
                                formapagos.add(formapago);
                                experiencias.add(experiencia);
                                zonass.add(zonas);
                                profesionespre.add(reputacion); //ESTE ES REPUTACIOOOONNN
                                telefonos.add(telefono);
                                latitudes.add(latitud);
                                longitudes.add(longitud);
                                distancespre.add(distauxiliar);
                                imagenes.add(imag);
                            }
                            //datos para grid
                            nombres=nombrespre;
                            profesiones=profesionespre;
                            ciudades=ciudadespre;
                            distances=distancespre;
                            cats=catspre;
                            //datos para intent
                            gridView.setAdapter(new ImageAdapter(getContext(),
                                    nombres,imagenes,profesiones,ciudades,distances,cats));

                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent vete = new Intent(getContext(),categoria.class);
                                    vete.putExtra("imagen",imagenes.get(position));
                                    vete.putExtra("idservidor",idservidores.get(position));
                                    vete.putExtra("cat",cats.get(position));
                                    vete.putExtra("nombre",nombrespre.get(position));
                                    vete.putExtra("ciudad",ciudadespre.get(position));
                                    vete.putExtra("estado",estados.get(position));
                                    vete.putExtra("descripcion",descripciones.get(position));
                                    vete.putExtra("precio",precios.get(position));
                                    vete.putExtra("formapago",formapagos.get(position));
                                    vete.putExtra("experiencia",experiencias.get(position));
                                    vete.putExtra("zonas",zonass.get(position));
                                    vete.putExtra("reputacion",profesionespre.get(position));
                                    vete.putExtra("telefono",telefonos.get(position));
                                    vete.putExtra("latitud",latitudes.get(position));
                                    vete.putExtra("longitud",longitudes.get(position));
                                    vete.putExtra("distance",distancespre.get(position));
                                    startActivity(vete);
                                }
                            });
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

    public void showDialog(final Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.setCancelable(true);
        View view  = activity.getLayoutInflater().inflate(R.layout.alertdialog, null);
        dialog.setContentView(view);
        Button dialogButton = dialog.findViewById(R.id.btnAlert);
        dialog.show();
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                dialog.dismiss();
                startActivity(new Intent(getContext(),MainActivity.class));
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }
}