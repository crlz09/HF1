package com.example.marci.hf1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by adminsoporte on 15/02/2018.
 */

public class ViewDialog extends anuncio{

    public void showDialog(final Activity activity, final String msg, final String num){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.anuncio_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);


        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //no esta funcionando el call, explota por: Fragment ViewDialog not attached to Activity

            //    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", num, null));
//                startActivity(intent);
                Toast.makeText(activity, "Aqui abre el dial con el numero: "+num, Toast.LENGTH_SHORT).show();
               dialog.dismiss();

            }


        });

        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }




}