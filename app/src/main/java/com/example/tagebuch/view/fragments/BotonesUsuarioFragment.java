package com.example.tagebuch.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tagebuch.R;


public class BotonesUsuarioFragment extends Fragment {


    private Button reportar,editar,eliminar;

    public BotonesUsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_botones_usuario, container, false);

        reportar = view.findViewById(R.id.ReportarPensamientoPrincipalButton);
        reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //cargamos fragmento reportar pensamiento
                Fragment fragment = new ReportarPensamientoFragment();
                //getSupportFragmentManager().beginTransaction().replace(R.id.ChangeFrameLayout,fragment).commit();
                FragmentTransaction transaction  = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.ChangeFrameLayout,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        editar = view.findViewById(R.id.EditarPensamientoPrincipalButton);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //cargamos fragmento editar pensamiento
                Fragment fragment = new ListarPensamientosEditarFragment();
                FragmentTransaction transaction  = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.ChangeFrameLayout,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        eliminar = view.findViewById(R.id.EliminarPensamientoPrincipalButton);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //cargamos fragmento eliminar pensamiento
                Fragment fragment = new EliminarPensamientoFragment();
                FragmentTransaction transaction  = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.ChangeFrameLayout,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        return view;
    }
}