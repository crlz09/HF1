package com.example.marci.hf1;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class categorias extends Fragment {

    LinearLayout categoriasss,categoria;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View vista = inflater.inflate(R.layout.nuevocat, container, false);


        final ArrayList<Integer> imagenes= new ArrayList();
        imagenes.add(R.drawable.belleza);
        imagenes.add(R.drawable.cuidador);
        imagenes.add(R.drawable.deportes);
        imagenes.add(R.drawable.entertainment);
        imagenes.add(R.drawable.hogar);
        imagenes.add(R.drawable.mascotas);
        imagenes.add(R.drawable.otros);
        imagenes.add(R.drawable.profesor);
        imagenes.add(R.drawable.reparacion);
        imagenes.add(R.drawable.salud);
        imagenes.add(R.drawable.transporte);

        final ArrayList<String> nombres = new ArrayList();
        nombres.add(getString(R.string.Belleza));
        nombres.add(getString(R.string.Cuiddor));
        nombres.add(getString(R.string.Deporte));
        nombres.add(getString(R.string.Entretenimiento));
        nombres.add(getString(R.string.Hogar));
        nombres.add(getString(R.string.Mascotas));
        nombres.add(getString(R.string.Otros));
        nombres.add(getString(R.string.Profesores));
        nombres.add(getString(R.string.Reparaciones));
        nombres.add(getString(R.string.Salud));
        nombres.add(getString(R.string.Transporte));

        final ArrayList<String> descripcion = new ArrayList();
        descripcion.add(getString(R.string.BellezaDesc));
        descripcion.add(getString(R.string.CuidadorDesc));
        descripcion.add(getString(R.string.DeporteDesc));
        descripcion.add(getString(R.string.EntretenimientoDesc));
        descripcion.add(getString(R.string.HogarDesc));
        descripcion.add(getString(R.string.MascotasDesc));
        descripcion.add(getString(R.string.OtrosDesc));
        descripcion.add(getString(R.string.ProfesoresDesc));
        descripcion.add(getString(R.string.ReparacionesDesc));
        descripcion.add(getString(R.string.SaludDesc));
        descripcion.add(getString(R.string.TransporteDesc));

        categoriasss =  vista.findViewById(R.id.LLroot);

        for (int i=0; i<nombres.size();i++) {
            categoria = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.nuevocat_element, null);
            LinearLayout base =  categoria.findViewById(R.id.nuevoelemento);
            ViewGroup.LayoutParams params = base.getLayoutParams();

          int dp=  (int) (170 * getContext().getResources().getDisplayMetrics().density + 0.5f);

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    /*width*/ ViewGroup.LayoutParams.MATCH_PARENT,
                   /*height*/ dp
            );

            base.setLayoutParams(param);

            final TextView nombre =  categoria.findViewById(R.id.nombreCAT);
            TextView desc =  categoria.findViewById(R.id.desCAT);
            base.setBackground(getResources().getDrawable(imagenes.get(i)));
            nombre.setText(nombres.get(i));
            desc.setText(descripcion.get(i));

            final int finalI = i;
            final int finalI1 = i;
            categoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent go = new Intent(getContext(),categoria_result.class);
                    go.putExtra("categoria",nombres.get(finalI1).toString().toUpperCase());
                    startActivity(go);
                }
            });

            categoriasss.addView(categoria);
        }

            return vista;

    }

}
