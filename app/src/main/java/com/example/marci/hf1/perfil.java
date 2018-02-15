package com.example.marci.hf1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class perfil extends AppCompatActivity {

    //boolean flag to know if main FAB is in open or closed state.
    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;

    //Linear layout holding the Save submenu
    private LinearLayout layoutFabSave;

    //Linear layout holding the Edit submenu
    private LinearLayout layoutFabEdit;
    private LinearLayout layoutFabPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Perfil del anunciante");
        final FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fabSpeedDial);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {

            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {

                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
               // Toast.makeText(perfil.this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                String phone = "04146525464";
                if (menuItem.getTitle().toString().equals("Llamar")){

                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    startActivity(intent);

                }
                if (menuItem.getTitle().toString().equals("SMS")){
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.putExtra("address", phone);
                    intent.putExtra("sms_body", "He visto tu publicación en HazloFacil, ");
                    intent.setData(Uri.parse("sms:"));
                    startActivity(intent);

                }
                if (menuItem.getTitle().toString().equals("Correo")){
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto: carlosjmr12@gmail.com"));
                    startActivity(Intent.createChooser(emailIntent, "He visto tu publicación en HazloFacil"));
                }


                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
//        fabSettings = (FloatingActionButton) this.findViewById(R.id.fab);





//        layoutFabSave = (LinearLayout) this.findViewById(R.id.layoutFabSave);
//        layoutFabEdit = (LinearLayout) this.findViewById(R.id.layoutFabSave1);
//        layoutFabPhoto = (LinearLayout) this.findViewById(R.id.layoutFabSave2);
//
//        //When main Fab (Settings) is clicked, it expands if not expanded already.
//        //Collapses if main FAB was open already.
//        //This gives FAB (Settings) open/close behavior
//        fabSettings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (fabExpanded == true){
//                    closeSubMenusFab();
//                } else {
//                    openSubMenusFab();
//                }
//            }
//        });
//
//        //Only main FAB is visible in the beginning
//        closeSubMenusFab();
//    }
//
//
//    //closes FAB submenus
//    private void closeSubMenusFab(){
//        layoutFabSave.setVisibility(View.INVISIBLE);
//        layoutFabEdit.setVisibility(View.INVISIBLE);
//        layoutFabPhoto.setVisibility(View.INVISIBLE);
//        fabSettings.setImageResource(R.drawable.ic_ring_volume_white_24px);
//        fabExpanded = false;
//    }
//
//    //Opens FAB submenus
//    private void openSubMenusFab(){
//        layoutFabSave.setVisibility(View.VISIBLE);
//        layoutFabEdit.setVisibility(View.VISIBLE);
//        layoutFabPhoto.setVisibility(View.VISIBLE);
//        //Change settings icon to 'X' icon
//        fabSettings.setImageResource(R.drawable.ic_clear_white_24px);
//        fabExpanded = true;
//    }

    }



}
