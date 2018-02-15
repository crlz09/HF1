package com.example.marci.hf1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class detalle_categorias extends AppCompatActivity {

    public elementoscercanos getItem(int position) {
        return elementoscercanos.ITEMS[position];
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevocer);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        String titulo = getIntent().getExtras().getString("nombre");
        this.setTitle(titulo);
        final GridView gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(new GridAdapter(getApplicationContext()));




        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final elementoscercanos item = getItem(position);

                // Toast.makeText(cercanos.this, "Tocaste un grid", Toast.LENGTH_SHORT).show();
                Intent vete = new Intent(getApplicationContext(),categoria.class);
                vete.putExtra("nombre",item.getNombre());

//                vete.putExtra("desc","No lo decimnos nosotros, lo dicen nuestros clientes: " +
//                        "EL MEJOR ESTABLECIMIENTO DE LA CIUDAD!");
//                vete.putExtra("tel","04146525464");
                vete.putExtra("idimagen",item.getIdDrawable());
//                vete.putExtra("ciudad",item.getCiudad());
//                vete.putExtra("distancia",item.getDistancia());
//                vete.putExtra("correo","prueba@prueba.com");
                //jugar con "int position", el es el que dice cual tocaste, jalar los datos del
                //array correspondiente (?) y pasarlos a la activity perfil

                startActivity(vete);
            }
        });


      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
}
