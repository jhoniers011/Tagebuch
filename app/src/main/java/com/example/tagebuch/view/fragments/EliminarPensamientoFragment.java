package com.example.tagebuch.view.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
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


public class EliminarPensamientoFragment extends Fragment implements PensamientosAdapter.ItemClickListener{



    private RecyclerView eliminarPensamientoRecyclerView;
    private MainActivityController mainActivityController;
    private PensamientosAdapter pensamientosAdapter;
    private DividerItemDecoration dividerItemDecoration;
    private LinearLayoutManager layoutManager;

    public EliminarPensamientoFragment() {
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
        View view = inflater.inflate(R.layout.fragment_eliminar_pensamiento, container, false);

        eliminarPensamientoRecyclerView = view.findViewById(R.id.EliminarPensamientoRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        eliminarPensamientoRecyclerView.setLayoutManager(layoutManager);

        dividerItemDecoration = new DividerItemDecoration(eliminarPensamientoRecyclerView.getContext(),layoutManager.getOrientation());

        eliminarPensamientoRecyclerView.addItemDecoration(dividerItemDecoration);
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
        builder.setTitle("¿Eliminar "+pensamiento.getTitulo() + " ?")
                .setMessage("Está seguro de eliminar este pensamiento")
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
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cargamos fragmento reportar pensamiento
                        Fragment fragment = new BotonesUsuarioFragment();
                        FragmentTransaction transaction  = getParentFragmentManager().beginTransaction();

                        transaction.replace(R.id.ChangeFrameLayout,fragment);
                        transaction.commit();
                    }
                })
                .show();
    }
}