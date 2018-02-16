package com.example.marci.hf1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class categoria extends AppCompatActivity implements OnMapReadyCallback {
    Double lat, lon;
    TextView descripcion,tel1,tel2,ubi1,ubi2,correo1,correo2, tvHotCall;
    ImageView ivperfil;
    LinearLayout comentarios;
    RelativeLayout hotCall;

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // MainActivity mainActivity = new MainActivity();

        lat=MainActivity.lat;
        lon=MainActivity.lon;
       // Toast.makeText(getApplicationContext(), ""+lat+"//"+lon, Toast.LENGTH_SHORT).show();
        String nombre = getIntent().getExtras().getString("nombre");
        int idimagen = getIntent().getExtras().getInt("idimagen");
//
//        String tel = getIntent().getExtras().getString("tel");
//        String ciudad = getIntent().getExtras().getString("ciudad");
//        String distancia=getIntent().getExtras().getString("distancia");
//        String correo=getIntent().getExtras().getString("correo");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_categoria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle(nombre);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//
//        descripcion = (TextView) findViewById(R.id.tvInfoEmpresa);
//        tel1=(TextView) findViewById(R.id.tvNumber1);
//        tel2=(TextView) findViewById(R.id.tvNumber2);
//        correo1=(TextView) findViewById(R.id.tvNumber3);
//        correo2=(TextView) findViewById(R.id.tvNumber4);
//        ubi1=(TextView) findViewById(R.id.tvNumber5);
//      //  ubi2=(TextView) findViewById(R.id.tvNumber6);
//
        Drawable drawable =getResources().getDrawable(idimagen);
        ivperfil= (ImageView) findViewById(R.id.IVperfil);
//        descripcion.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quisquam magni consequatur architecto nobis neque atque, officia laudantium cum dolore tempora eius nam repudiandae blanditiis consectetur");


        Glide.with(getApplicationContext()).load("").placeholder(idimagen).centerCrop().into(ivperfil);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent vete = new Intent(getApplicationContext(),perfil.class);

                startActivity(vete);
//
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        // ivperfil= (ImageView) findViewById(R.id.IVperfil);
       /* Glide.with(getApplicationContext()).load(img).into(ivperfil);*/

       comentarios= (LinearLayout) findViewById(R.id.valoracionLL);

       comentarios.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent vete = new Intent(getApplicationContext(),comentarios.class);
              //pasar idpub para cargar comentarios de esa pub (?)
               startActivity(vete);
           }
       });


       hotCall = (RelativeLayout) findViewById(R.id.RLHotCall);
        tvHotCall=(TextView) findViewById(R.id.tvHotCall);

       hotCall.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String phone = tvHotCall.getText().toString();
               Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
               startActivity(intent);

           }
       });
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Aquí está"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 14.0f ) );

        Toast.makeText(this, ""+MainActivity.ultimadireccion.toString(), Toast.LENGTH_SHORT).show();

    }
}
