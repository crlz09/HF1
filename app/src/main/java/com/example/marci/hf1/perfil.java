package com.example.marci.hf1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.yavski.fabspeeddial.FabSpeedDial;

public class perfil extends AppCompatActivity {


    // json object response url
    private String urlJsonObj = "https://api.androidhive.info/volley/person_object.json";
    private String urlJsonArry = "http://www.ksfactory.com.ve/cercanos.php?cercat=&cat=";
    private String urlPerfil="http://www.ksfactory.com.ve/cercanos.php?perfil=&id=";
    private String urlHabilidades="http://www.ksfactory.com.ve/cercanos.php?habilidad=&id=";
    private static String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private String jsonResponse;
    CircleImageView perfilimage;


    //boolean flag to know if main FAB is in open or closed state.
    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;
    TextView acercade,habilidadesde,descripcion,estudio,nombre,tlf;

    //Linear layout holding the Save submenu
    private LinearLayout layoutFabSave;

    //Linear layout holding the Edit submenu
    private LinearLayout layoutFabEdit,raizestudios,raizhabilidades;
    private LinearLayout layoutFabPhoto;
    int iduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Perfil del anunciante");
        final FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fabSpeedDial);
        iduser = getIntent().getExtras().getInt("idusuario");
        Toast.makeText(this, "" + iduser, Toast.LENGTH_SHORT).show();
         raizestudios= findViewById(R.id.LLRaizEstudios);
         raizhabilidades= findViewById(R.id.LLRaizHabilidades);


        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {

            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {

                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                // Toast.makeText(perfil.this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                String phone = "04146525464";
                if (menuItem.getTitle().toString().equals("Llamar")) {

                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    startActivity(intent);

                }
                if (menuItem.getTitle().toString().equals("SMS")) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.putExtra("address", phone);
                    intent.putExtra("sms_body", "He visto tu publicación en HazloFacil, ");
                    intent.setData(Uri.parse("sms:"));
                    startActivity(intent);

                }
                if (menuItem.getTitle().toString().equals("Correo")) {
                    String to = "carlosjmr12@gmail.com";
                    String subject = "Vi tu anuncio en HazloFacil";
                    String body = "Vi tu anuncio en HazloFacil, ";
                    String mailTo = "mailto:" + to +
                            "?&subject=" + Uri.encode(subject) +
                            "&body=" + Uri.encode(body);
                    Intent emailIntent = new Intent(Intent.ACTION_VIEW);
                    emailIntent.setData(Uri.parse(mailTo));
                    startActivity(emailIntent);
                }


                return true;

            }

            @Override
            public void onMenuClosed() {

            }
        });

        makeJsonArrayRequest(urlPerfil+iduser);
        makeJsonArrayRequest(urlHabilidades+iduser);
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
    private void makeJsonArrayRequest(final String url) {

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


                            if(url.contains("http://www.ksfactory.com.ve/cercanos.php?perfil=&id="))
                            {   //capturo los datos de pefil


                                acercade=findViewById(R.id.tvInfo);
                                descripcion=findViewById(R.id.tvInfoEmpresa);
                                habilidadesde=findViewById(R.id.tvHabilidades);
                                nombre=findViewById(R.id.tvNombre);
                                perfilimage=findViewById(R.id.profile_image);

                                for (int i = 0; i < response.length(); i++) {

                                    JSONObject person = (JSONObject) response
                                            .get(i);
                                    nombre.setText( person.getString("nombre"));
                                    descripcion.setText( person.getString("descripcion"));
                                    acercade.setText("Acerca de "+ person.getString("nombre"));
                                    habilidadesde.setText("Habilidades de "+ person.getString("nombre"));
                                    Glide.with(getApplicationContext()).load(person.getString("imagen")).centerCrop().into(perfilimage);
                                                                    }






                            } else if(url.contains("http://www.ksfactory.com.ve/cercanos.php?habilidad=&id=")){
                                //CAPTURO DATOS DE HABILIDADES

                                ArrayList<String> habilidad = new ArrayList<>();
                                ArrayList<String> estudio = new ArrayList<>();

                                for (int i = 0; i < response.length(); i++) {

                                    JSONObject person = (JSONObject) response
                                            .get(i);
                                    String desc= person.getString("habilidad");
                                    String tipo= person.getString("tipo");
                                  if(tipo.equals("HABILIDAD")){
                                      habilidad.add(desc);
                                  } else {estudio.add(desc);}

                                }

                                LinearLayout texto = (LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.textforprofile, null);
                                LinearLayout base = texto.findViewById(R.id.LLBase);
                                TextView txt = texto.findViewById(R.id.txt);

                                for(int i=0; i<estudio.size(); i++){
                                    txt.setText(estudio.get(i));
                                    raizestudios.addView(texto);
                                }
                                for(int i=0; i<habilidad.size();i++){
                                    txt.setText(habilidad.get(i));
                                    raizhabilidades.addView(texto);
                                }


                            }





                            //datos para intent


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
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hidepDialog();
            }
        });

        req.setShouldCache(false);
        Volley.newRequestQueue(getApplicationContext()).add(req);
    }

}
