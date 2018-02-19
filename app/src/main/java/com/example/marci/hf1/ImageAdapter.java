package com.example.marci.hf1;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class ImageAdapter extends BaseAdapter {

    private ArrayList<Integer> images;
    private ArrayList<String> nombres,profesiones,ciudades,cats;
    private ArrayList<String> distances;
    LayoutInflater inflater;
    private Context context;

    public ImageAdapter(Context context, ArrayList<String> nombre, ArrayList<Integer> drawables, ArrayList<String> profesion,
                        ArrayList<String> ciudad, ArrayList<String> distancia,ArrayList<String> cats) {


        this.images = drawables;
        this.nombres=nombre;
        this.profesiones=profesion;
        this.ciudades=ciudad;
        this.distances=distancia;
        this.context=context;
        this.cats=cats;
      //          inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {

        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView pinImagen = (ImageView) view.findViewById(R.id.pin);
        final LinearLayout layfondo = (LinearLayout) view.findViewById(R.id.layFondo);
        TextView nombre = (TextView) view.findViewById(R.id.nombreLay);
        TextView descripcion = (TextView) view.findViewById(R.id.descripcioLay);
        TextView distancia = (TextView) view.findViewById(R.id.distanciaTV);
        RatingBar ratingBar=(RatingBar) view.findViewById(R.id.ratingBar);

        Glide.with(context).load(images.get(position)).asBitmap().into(new SimpleTarget<Bitmap>() {

            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    layfondo.setBackground(drawable);
                }
            }
        });

        String nombreaux;

        if (nombres.get(position).length()>20){
             nombreaux= nombres.get(position).substring(0,20)+"..";
        } else nombreaux=nombres.get(position);


        ratingBar.setStepSize(0.5f);
        pinImagen.setImageResource(R.drawable.pin);
        nombre.setText(nombreaux);
        descripcion.setText("en "+cats.get(position));
        ratingBar.setRating(Float.parseFloat(profesiones.get(position)));
        distancia.setText(distances.get(position).toString()+"KM");

        return view;
    }



}