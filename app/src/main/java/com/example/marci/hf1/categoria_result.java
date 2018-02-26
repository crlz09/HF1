package com.example.marci.hf1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class categoria_result extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // json object response url
    private String urlJsonObj = "https://api.androidhive.info/volley/person_object.json";
    private String urlJsonArry = "http://www.ksfactory.com.ve/cercanos.php?cercat=&cat=";
    private String urlsola="http://www.ksfactory.com.ve/cercanos.php?categoria=&cat=";
    private String urlfinal;
    private static String TAG = MainActivity.class.getSimpleName();

    // Progress dialog
    private ProgressDialog pDialog;

    public  Double lon, lat;

    // temporary string to show the parsed response
    private String jsonResponse;

    String idservidor,cat,nombre,ciudad,estado,descripcion,precio,formapago,experiencia,zonas,reputacion,telefono,imag;
    Double latitud,longitud,distance;
    public String cate;
    public static ArrayList<Integer> idusuarios;
    public static ArrayList<String> nombres,profesiones,ciudades,distances,cats;
    public GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevocer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = findViewById(R.id.grid);
        cate = getIntent().getExtras().getString("categoria");
        lon=MainActivity.lon;
        lat=MainActivity.lat;
        this.setTitle(cate);
        pDialog = new ProgressDialog(categoria_result.this);
        pDialog.setMessage("Filtrando cercanos...");
        pDialog.setCancelable(false);
        makeJsonArrayRequest();
        System.out.println(jsonResponse);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


        View header = navigationView.getHeaderView(0);

        TextView navuser= header.findViewById(R.id.profile_name_nav);
        CircleImageView imageView = header.findViewById(R.id.profile_image_nav);
/*        if (llego){
            navuser.setText(nombreuserfinal);
            Glide.with(getApplicationContext()).load(imagenfinal).centerCrop().into(imageView);

        }*/

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
        getMenuInflater().inflate(R.menu.main, menu);
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
        FragmentManager fragmentManager= getSupportFragmentManager();

        if (id == R.id.menu_home) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new fragmentMain()).commit();
        } else if (id == R.id.menu_notif) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new categorias()).commit();
        } else if (id == R.id.menu_fav) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new destacados()).commit();
        } else if (id == R.id.misAnuncios) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new cercanos()).commit();
        } else if (id == R.id.miCuenta) {

        } else if (id == R.id.menureport) {

        } else if (id == R.id.menuacercade) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }




      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


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

                    Toast.makeText(getApplicationContext(), ""+jsonResponse, Toast.LENGTH_LONG).show();
                    // txtResponse.setText(jsonResponse);

                } catch (JSONException e) {
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
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        Volley.newRequestQueue(getApplicationContext()).add(jsonObjReq);
    }





    private void makeJsonArrayRequest() {

        showpDialog();
        if(lat!=null) {urlfinal= urlJsonArry+cate+"&lat="+lat+"&lng="+lon;
        } else urlfinal=urlsola+cate;

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

                            //     System.out.println(jsonResponse);
                            final ArrayList<String> idservidores=new ArrayList<>();
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
                            final ArrayList<Integer> idusuario=new ArrayList<>();

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
                                idusuario.add(person.getInt("idusuario"));

                                imag = person.getString("imagen");
                                String distauxiliar="";
                                try {
                                    distance = person.getDouble("distance");
                                    if(distance!=null){
                                        distauxiliar= distance.toString().substring(0,3);}
                                        else {distauxiliar="N/A";}

                                } catch (Exception exception){
                                    distauxiliar="N/A ";
                                }

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
                            idusuarios=idusuario;
                            nombres=nombrespre;
                            profesiones=profesionespre;
                            ciudades=ciudadespre;
                            distances=distancespre;
                            cats=catspre;

                            gridView.setAdapter(new ImageAdapter(getApplicationContext(),
                                    nombres,imagenes,profesiones,ciudades,distances,cats));



                            //datos para intent


                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Intent vete = new Intent(getApplicationContext(),categoria.class);
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
                                    vete.putExtra("idusuario",idusuarios.get(position));

                                    vete.putExtra("distance",distancespre.get(position));

                                    startActivity(vete);



                                    // Toast.makeText(cercanos.this, "Tocaste un grid", Toast.LENGTH_SHORT).show();

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();




                        if(lat!=null) {
                        } else {
                            final Snackbar mySnackbar = Snackbar.make(findViewById(R.id.coordinator),
                                    "Resultados sin GPS", Snackbar.LENGTH_LONG);
                            mySnackbar.setAction("ENTENDIDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mySnackbar.dismiss();
                                }
                            });
                            mySnackbar.show();

                        }


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

    public void nada(){
        Toast.makeText(this, "Le diste a entendido", Toast.LENGTH_SHORT).show();
    }


}
