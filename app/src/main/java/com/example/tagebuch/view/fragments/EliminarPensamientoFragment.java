package com.example.tagebuch.view.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tagebuch.Adapter.PensamientosAdapter;
import com.example.tagebuch.R;
import com.example.tagebuch.controller.MainActivityController;
import com.example.tagebuch.model.pojo.Pensamiento;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EliminarPensamientoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EliminarPensamientoFragment extends Fragment implements PensamientosAdapter.ItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView eliminarPensamientoRecyclerView;
    private MainActivityController mainActivityController;
    private PensamientosAdapter pensamientosAdapter;

    public EliminarPensamientoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EliminarPensamientoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EliminarPensamientoFragment newInstance(String param1, String param2) {
        EliminarPensamientoFragment fragment = new EliminarPensamientoFragment();
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

        View view = inflater.inflate(R.layout.fragment_eliminar_pensamiento, container, false);

        eliminarPensamientoRecyclerView = view.findViewById(R.id.EliminarPensamientoRecyclerView);
        eliminarPensamientoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainActivityController = new MainActivityController();

        List<Pensamiento> listapensamientos;

        listapensamientos = ListarPensamientos(mainActivityController);

        pensamientosAdapter = new PensamientosAdapter(listapensamientos,getContext(),this);
        eliminarPensamientoRecyclerView.setAdapter(pensamientosAdapter);


        return view;
    }

    public List<Pensamiento> ListarPensamientos(MainActivityController mainActivityController){

        return mainActivityController.listarPensamiento(getActivity());

    }

    @Override
    public void OnItemClick(Pensamiento pensamiento) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("¿Eliminar "+pensamiento.getTitulo())
                .setMessage("Está seguro de eliminar " + pensamiento.getTitulo())
                //.setCancelable(false)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                        eliminarPensamiento(pensamiento);

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })

                .show();
    }

    public void eliminarPensamiento(Pensamiento pensamiento){
        mainActivityController.eliminarPensamiento(this,pensamiento);

    }

    public void eliminarPensamientoCorrecto(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Correcto")
                .setMessage("Pensamiento eliminado correctamente")
                //.setCancelable(false)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cargamos fragmento reportar pensamiento
                        Fragment fragment = new BotonesUsuarioFragment();
                        //getSupportFragmentManager().beginTransaction().replace(R.id.ChangeFrameLayout,fragment).commit();
                        FragmentTransaction transaction  = getParentFragmentManager().beginTransaction();

                        transaction.replace(R.id.ChangeFrameLayout,fragment);
                        transaction.commit();
                    }
                })
                .show();
    }
}