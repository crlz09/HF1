package com.example.marci.hf1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
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

    String idservidor,cat,nombre,ciudad,estado,descripcion,precio,formapago,experiencia,zonas,reputacion,telefono;
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

        lon=MainActivity.lon;
        lat=MainActivity.lat;




        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Filtrando cercanos...");
        pDialog.setCancelable(false);
        makeJsonArrayRequest();
        System.out.println(jsonResponse);
        /*//        if (jsonResponse.equals("yes")){
            Toast.makeText(getContext(), "YESSSS", Toast.LENGTH_SHORT).show();

 //       }*/


        //CONTROLAR EL JSON PARA CUANDO LOCATION OFF

         //   Toast.makeText(getContext(), "JSONEEEE", Toast.LENGTH_SHORT).show();



//            Context context, ArrayList<String> nombre, ArrayList<Integer> drawables, ArrayList<String> profesion,
//                    ArrayList<String> ciudad, ArrayList<Double> distancia) {








        return  vista;
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



    //object y array request:

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
                    String email = response.getString("email");
                    JSONObject phone = response.getJSONObject("phone");
                    String home = phone.getString("home");
                    String mobile = phone.getString("mobile");

                    jsonResponse = "";
                    jsonResponse += "Name: " + name + "\n\n";
                    jsonResponse += "Email: " + email + "\n\n";
                    jsonResponse += "Home: " + home + "\n\n";
                    jsonResponse += "Mobile: " + mobile + "\n\n";

                    Toast.makeText(getContext(), ""+jsonResponse, Toast.LENGTH_LONG).show();
                    // txtResponse.setText(jsonResponse);

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
       // Toast.makeText(getContext(), ""+urlfinal, Toast.LENGTH_LONG).show();
        JsonArrayRequest req = new JsonArrayRequest(urlfinal,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        int imagenesdefault [] = {R.drawable.launo,R.drawable.lados,R.drawable.latres,R.drawable.lacuatro,R.drawable.lacinco,R.drawable.laseis,
                                R.drawable.lasiete,R.drawable.laocho,R.drawable.launo,
                                R.drawable.launo,R.drawable.lados,R.drawable.latres,R.drawable.lacuatro,R.drawable.lacinco,R.drawable.laseis,
                                R.drawable.lasiete,R.drawable.laocho,R.drawable.launo,
                                R.drawable.launo,R.drawable.lados,R.drawable.latres,R.drawable.lacuatro,R.drawable.lacinco,R.drawable.laseis,
                                R.drawable.lasiete,R.drawable.laocho,R.drawable.launo,
                                R.drawable.launo,R.drawable.lados,R.drawable.latres,R.drawable.lacuatro,R.drawable.lacinco,R.drawable.laseis,
                                R.drawable.lasiete,R.drawable.laocho,R.drawable.launo,
                                R.drawable.launo,R.drawable.lados,R.drawable.latres,R.drawable.lacuatro,R.drawable.lacinco,R.drawable.laseis,
                                R.drawable.lasiete,R.drawable.laocho,R.drawable.launo,
                                R.drawable.launo,R.drawable.lados,R.drawable.latres,R.drawable.lacuatro,R.drawable.lacinco,R.drawable.laseis,
                                R.drawable.lasiete,R.drawable.laocho,R.drawable.launo,
                                R.drawable.launo,R.drawable.lados,R.drawable.latres,R.drawable.lacuatro,R.drawable.lacinco,R.drawable.laseis,
                                R.drawable.lasiete,R.drawable.laocho,R.drawable.launo,
                                R.drawable.launo,R.drawable.lados,R.drawable.latres,R.drawable.lacuatro,R.drawable.lacinco,R.drawable.laseis,
                                R.drawable.lasiete,R.drawable.laocho,R.drawable.launo,
                                R.drawable.launo,R.drawable.lados,R.drawable.latres,R.drawable.lacuatro,R.drawable.lacinco,R.drawable.laseis,
                                R.drawable.lasiete,R.drawable.laocho,R.drawable.launo,
                                R.drawable.launo,R.drawable.lados,R.drawable.latres,R.drawable.lacuatro,R.drawable.lacinco,R.drawable.laseis,
                                R.drawable.lasiete,R.drawable.laocho,R.drawable.launo};

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "yes";
                            // Toast.makeText(MainActivity.this, "entro", Toast.LENGTH_SHORT).show();
                            //hidepDialog();

                       //     System.out.println(jsonResponse);
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

                                String distauxiliar= distance.toString().substring(0,3);
                                imagespre.add(imagenesdefault[i]);

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
                            }

                            //datos para grid
                            images=imagespre;
                            nombres=nombrespre;
                            profesiones=profesionespre;
                            ciudades=ciudadespre;
                            distances=distancespre;
                            cats=catspre;

                            //datos para intent
                            gridView.setAdapter(new ImageAdapter(getContext(),
                                    nombres,images,profesiones,ciudades,distances,cats));

                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Intent vete = new Intent(getContext(),categoria.class);
                                    vete.putExtra("imagen",imagespre.get(position));
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



                                    // Toast.makeText(cercanos.this, "Tocaste un grid", Toast.LENGTH_SHORT).show();

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


}
