package com.example.marci.hf1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("HAZLOFACIL");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
       //toolbar.setLogo(R.drawable.creoquesi);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Track.ttf");

        for(int i = 0; i < toolbar.getChildCount(); i++)
        { View view = toolbar.getChildAt(i);

            if(view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setTypeface(face);
               }


        }





        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Bienvenido al chat!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent abrirchat = new Intent(getApplicationContext(), perguntas.class);
                startActivity(abrirchat);
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager= getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.contenedor,new fragmentMain()).commit();
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

/*
        } else if (id == R.id.nav_send) {
*/

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    /*class MyAdapter extends BaseAdapter {
        private Context context;
        String[] opciones;
        int[] images={R.drawable.home_icon,R.drawable.notif_icon,R.drawable.fav_icon,R.drawable.anuncios_icon,
        R.drawable.myaccount_icon,R.drawable.config_icon,R.drawable.troubleshoot_icon,R.drawable.acercade_icon};

        public MyAdapter(Context context) {
            this.context=context;
            opciones=context.getResources().getStringArray(R.array.menuoptions);

        }

        @Override
        public int getCount() {
            return opciones.length;
        }

        @Override
        public Object getItem(int position) {
            return opciones[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View menudrawer=null;

            if(convertView==null){
                LayoutInflater inflater =(LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                menudrawer=inflater.inflate(R.layout.pruebamenu,parent,false);


            } else {
                menudrawer=convertView;
            }
            TextView tv1= (TextView) menudrawer.findViewById(R.id.tvInicio);
            ImageView iv1= (ImageView) menudrawer.findViewById(R.id.ivmenu0);

            return null;
        }
    }*/
}
