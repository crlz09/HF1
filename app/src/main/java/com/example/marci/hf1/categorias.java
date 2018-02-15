package com.example.marci.hf1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class categorias extends Fragment {

    LinearLayout belleza;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista = inflater.inflate(R.layout.nuevocat, container, false);
        belleza=(LinearLayout)vista.findViewById(R.id.bellezaCat);

        belleza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vete = new Intent(getContext(),detalle_categorias.class);
                vete.putExtra("nombre","Belleza");

                startActivity(vete);
            }
        });

       /*&& Display display = getActivity().getWindowManager().getDefaultDisplay();
        int stageWidth = display.getWidth();

        Toast.makeText(getContext(), ""+stageWidth, Toast.LENGTH_SHORT).show();
        LinearLayout catr = (LinearLayout)vista.findViewById(R.id.categoriarosa);
        LinearLayout catce = (LinearLayout)vista.findViewById(R.id.categoriaceleste);
        LinearLayout catmo = (LinearLayout)vista.findViewById(R.id.categoriamorada);
        LinearLayout catam = (LinearLayout)vista.findViewById(R.id.categoriaamarilla);

        ViewGroup.LayoutParams lp1 = catr.getLayoutParams();
        lp1.width=stageWidth/3;

        ViewGroup.LayoutParams lp2 = catce.getLayoutParams();
        lp2.width=stageWidth/3;

        ViewGroup.LayoutParams lp3 = catmo.getLayoutParams();
        lp3.width=stageWidth/3;

        ViewGroup.LayoutParams lp4 = catam.getLayoutParams();
        lp4.width=stageWidth/3;
*/

/*        LinearLayout ll1 = (LinearLayout)vista.findViewById(R.id.ll1);
        LinearLayout ll2 = (LinearLayout)vista.findViewById(R.id.ll2);
        LinearLayout ll3 = (LinearLayout)vista.findViewById(R.id.ll3);*/
        LinearLayout lay = (LinearLayout)vista.findViewById(R.id.result1);
       // TextView tv = (TextView)vista.findViewById(R.id.categName);
      //  tv.setText(""+lay.getMeasuredWidth());


        return vista;

    }

}
