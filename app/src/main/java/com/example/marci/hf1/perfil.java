package com.example.marci.hf1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
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
import android.view.ViewGroup;
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
    private String urlPerfil="http://www.ksfactory.com.ve/cercanos.php?perfil_hab=&id=";
    private String urlCantidad="http://www.ksfactory.com.ve/cercanos.php?rep_cant=&id=";
    String mailfinal,numfinal;


    private static String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private String jsonResponse;
    CircleImageView perfilimage;

    ArrayList<String> habilidad = new ArrayList<>();
    ArrayList<String> estudio = new ArrayList<>();
    //boolean flag to know if main FAB is in open or closed state.
    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;
    TextView acercade,habilidadesde,descripcion,estudios,nombre,tlf;

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
       // Toast.makeText(this, "" + iduser, Toast.LENGTH_SHORT).show();

        raizhabilidades= findViewById(R.id.LLRaizHabilidades);
        raizestudios=findViewById(R.id.LLRaizEstudios);

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

                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numfinal, null));
                    startActivity(intent);

                }
                if (menuItem.getTitle().toString().equals("SMS")) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.putExtra("address", numfinal);
                    intent.putExtra("sms_body", "He visto tu publicación en HazloFacil, ");
                    intent.setData(Uri.parse("sms:"));
                    startActivity(intent);

                }
                if (menuItem.getTitle().toString().equals("Correo")) {
                    String to = mailfinal;
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

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Filtrando cercanos...");
        pDialog.setCancelable(false);
      //  Toast.makeText(this, "elid: "+iduser, Toast.LENGTH_SHORT).show();
        makeJsonArrayRequest(urlPerfil+iduser,0);
        makeJsonArrayRequest(urlCantidad+iduser,1);


        LinearLayout LLValoracion= findViewById(R.id.LLValoracion);
        LinearLayout LLPublicaciones= findViewById(R.id.LLPublicaciones);

        LLValoracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chao = new Intent(getApplicationContext(), valoraciones.class);
                chao.putExtra("iduser",iduser);
                startActivity(chao);
            }
        });

        LLPublicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chao = new Intent(getApplicationContext(), MisPublicaciones.class);
                chao.putExtra("iduser",iduser);
                startActivity(chao);
            }
        });


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
    private void makeJsonArrayRequest(final String url, final int tipo) {

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


                            if(tipo==0)
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
                                    String desc = person.getString("habilidad");
                                    String tipo = person.getString("tipo");
                                    mailfinal= person.getString("email");
                                    numfinal=person.getString("telefono");

                                    if (tipo.equals("HABILIDAD")) {
                                        habilidad.add(desc);
                                        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                                        View inflatedLayout = inflater.inflate(R.layout.textforprofile,null);
                                        TextView textView=inflatedLayout.findViewById(R.id.txt);
                                        textView.setText(textView.getText()+desc);
                                        raizhabilidades.addView(inflatedLayout);

                                    } else {
                                        estudio.add(desc);
                                        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                                        View inflatedLayout = inflater.inflate(R.layout.textforprofile,null);
                                        TextView textView=inflatedLayout.findViewById(R.id.txt);
                                        textView.setText(textView.getText()+desc);
                                        raizestudios.addView(inflatedLayout);
                                    }
                                                                    }


                            } else if(tipo==1) {

                                TextView valoracion= findViewById(R.id.valorTV);
                                TextView publicaciones=findViewById(R.id.TVPublicaciones);
                                for (int i = 0; i < response.length(); i++) {

                                    JSONObject person = (JSONObject) response
                                            .get(i);
                                    String suma=person.getString("SUMA");
                                    String casival= suma.substring(0,3)+" / 5";
                                    valoracion.setText(casival);
                                    publicaciones.setText( person.getString("PUBLICACIONES"));

                                }

                            }

/*                            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                            View inflatedLayout = inflater.inflate(R.layout.textforprofile,null,false);
                            TextView textView=inflatedLayout.findViewById(R.id.txt);
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
