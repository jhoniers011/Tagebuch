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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button reportar,editar,eliminar;

    public BotonesUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BotonesUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BotonesUsuarioFragment newInstance(String param1, String param2) {
        BotonesUsuarioFragment fragment = new BotonesUsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
                transaction.commit();
            }
        });

        editar = view.findViewById(R.id.EditarPensamientoPrincipalButton);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //cargamos fragmento editar pensamiento
                Fragment fragment = new ListarPensamientosEditarFragment();
                //getSupportFragmentManager().beginTransaction().replace(R.id.ChangeFrameLayout,fragment).commit();
                FragmentTransaction transaction  = getParentFragmentManager().beginTransaction();

                transaction.replace(R.id.ChangeFrameLayout,fragment);
                transaction.commit();

            }
        });

        eliminar = view.findViewById(R.id.EliminarPensamientoPrincipalButton);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //cargamos fragmento eliminar pensamiento
                Fragment fragment = new EliminarPensamientoFragment();
                //getSupportFragmentManager().beginTransaction().replace(R.id.ChangeFrameLayout,fragment).commit();
                FragmentTransaction transaction  = getParentFragmentManager().beginTransaction();

                transaction.replace(R.id.ChangeFrameLayout,fragment);
                transaction.commit();

            }
        });

        return view;
    }
}