package com.example.marci.hf1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by carlosm on 03/11/2017.
 */

public class perguntas extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
EditText ETrespuesta;
ImageView IVenviar;
LinearLayout paparespuestas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.situeresmihombre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ETrespuesta = (EditText) findViewById(R.id.ETRespuesta);
        IVenviar = (ImageView) findViewById(R.id.IVSend);

        IVenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(ETrespuesta.getText());
               // Toast.makeText(perguntas.this, "Enviado: " + ETrespuesta.getText(), Toast.LENGTH_SHORT).show();
                String escrito = ETrespuesta.getText().toString();
                paparespuestas=(LinearLayout) findViewById(R.id.papadelasrespuestas);

                LinearLayout nuevomensaje;
                TextView tvinflado;
                if (escrito.equals("Derecha")){
                    nuevomensaje=(LinearLayout) LayoutInflater.from(getApplicationContext())
                            .inflate(R.layout.ladoder,null);



                    tvinflado=(TextView) nuevomensaje.findViewById(R.id.TVderecha);
                    tvinflado.setText(escrito);


                }

                else { nuevomensaje=(LinearLayout) LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.ladoizq,null);



                    tvinflado=(TextView) nuevomensaje.findViewById(R.id.TVizquierda);
                    tvinflado.setText(escrito);



                }

                paparespuestas.addView(nuevomensaje);
                ETrespuesta.setText("");
                }
            });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

         this.setTitle("USUARIO123");
         this.setTitleColor(Color.parseColor("#FFFFFF"));
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

     /*   if (id == R.id.nav_camera) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new fragmentMain()).commit();
        } else if (id == R.id.nav_gallery) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new categorias()).commit();
        } else if (id == R.id.nav_slideshow) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new destacados()).commit();
        } else if (id == R.id.nav_manage) {
            fragmentManager.beginTransaction().replace(R.id.contenedor,new cercanos()).commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
