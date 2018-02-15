package com.example.marci.hf1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;


public class cercanos extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.nuevocer, container, false);

        GridView gridView = (GridView) vista.findViewById(R.id.grid);
        gridView.setAdapter(new GridAdapter(getContext()));

        return  vista;
    }


}
