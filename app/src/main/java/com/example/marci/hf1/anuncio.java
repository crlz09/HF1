package com.example.marci.hf1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class anuncio extends Fragment {

    LinearLayout anuncio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_anuncio, container, false);
        anuncio=(LinearLayout) vista.findViewById(R.id.anuncio_laypadre1);

        //cadena de prueba
        final String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed nisl urna, facilisis sed porttitor eget, pretium sit amet sapien. Quisque ut diam quis nibh fermentum pharetra. Proin interdum lectus libero, vitae fermentum felis tempus vel. Etiam eu nisi odio. Nullam tincidunt volutpat augue sed porttitor. Vestibulum dapibus elementum nisi, vitae tincidunt odio sollicitudin ut. Maecenas ligula lacus, blandit ut congue eu, pretium sed ante. Praesent quis odio mauris. Nullam rutrum sollicitudin quam, tristique faucibus quam facilisis et. In dignissim lectus posuere nunc pulvinar posuere. Nam vitae justo a lorem lobortis rutrum ac tristique massa. Quisque id scelerisque mi. Integer justo neque, venenatis interdum pharetra eget, mattis vel lorem.";
        final String num="04146525464";

        anuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDialog(getActivity(), lorem,num);
            }
        });

        return  vista;

    }

//DIALOG:------------------------------------------------------------------

    public void showDialog(final Activity activity, final String msg, final String num){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);

        View view  = getActivity().getLayoutInflater().inflate(R.layout.anuncio_dialog, null);
        dialog.setContentView(view);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);


        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", num, null));
                startActivity(intent);
                dialog.dismiss();

            }

        });

        dialog.show();

        dialog.setCanceledOnTouchOutside(true);
    }

}