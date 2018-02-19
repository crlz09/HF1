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
import android.widget.RatingBar;
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
    Double lat, lon,latitud,longitud;
    TextView tvdescripcion,tel1,tel2,ubi1,ubi2,correo1,correo2, tvHotCall;
    ImageView ivperfil;
    LinearLayout comentarios;
    TextView tvciudad,tvprecio,tvformapago,tvexperiencia,tvfechapub,tvzonas;
    RelativeLayout hotCall;
    RatingBar ratingBar;

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // MainActivity mainActivity = new MainActivity();

        lat=MainActivity.lat;
        lon=MainActivity.lon;
     //   Toast.makeText(getApplicationContext(), ""+lat+"//"+lon, Toast.LENGTH_SHORT).show();

        String idservidor = getIntent().getExtras().getString("idservidor");
        String cat = getIntent().getExtras().getString("cat");
        String nombre = getIntent().getExtras().getString("nombre");
        String ciudad = getIntent().getExtras().getString("ciudad");
        String estado = getIntent().getExtras().getString("estado");
        String descripcion = getIntent().getExtras().getString("descripcion");
        String precio = getIntent().getExtras().getString("precio");
        String formapago = getIntent().getExtras().getString("formapago");
        String experiencia = getIntent().getExtras().getString("experiencia");
        String zonas = getIntent().getExtras().getString("zonas");
        String reputacion = getIntent().getExtras().getString("reputacion");
        final String telefono = getIntent().getExtras().getString("telefono");
         latitud = getIntent().getExtras().getDouble("latitud");
         longitud = getIntent().getExtras().getDouble("longitud");
        String distance = getIntent().getExtras().getString("distance");
        int idimagen = getIntent().getExtras().getInt("imagen");

       /* vete.putExtra("idservidor",idservidores.get(position));
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
        vete.putExtra("distance",distancespre.get(position));*/

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

    tvdescripcion=(TextView) findViewById(R.id.tvDescripcion);
    tvciudad=(TextView) findViewById(R.id.tvCiudadEstado);
    tvprecio=(TextView) findViewById(R.id.tvPrecio);
    tvformapago=(TextView) findViewById(R.id.tvFormapago);
    tvexperiencia=(TextView) findViewById(R.id.tvExperiencia);
    tvfechapub=(TextView) findViewById(R.id.tvFechapub);
    tvzonas=(TextView) findViewById(R.id.tvZonas);
    ratingBar=(RatingBar) findViewById(R.id.RBreputacion);


    //FALTA COMENTARIOS Y FECHA DE PUBLICACION

    tvdescripcion.setText(descripcion);
    tvciudad.setText(ciudad+" - "+estado+" - A "+distance+" KM DE TI");
    tvprecio.setText(precio);
    tvformapago.setText(formapago);
    tvexperiencia.setText(experiencia);
    tvfechapub.setText("Hace 2 semanas");
    tvzonas.setText(zonas);
    ratingBar.setRating(Float.parseFloat(reputacion));









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
               Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null));
               startActivity(intent);

           }
       });
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Aquí está"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 14.0f ) );

      //  Toast.makeText(this, ""+MainActivity.ultimadireccion.toString(), Toast.LENGTH_SHORT).show();

    }
}
