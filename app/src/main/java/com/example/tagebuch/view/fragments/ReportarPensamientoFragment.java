package com.example.tagebuch.view.fragments;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.TextView;

import com.example.tagebuch.Adapter.CategoriasAdapter;
import com.example.tagebuch.R;
import com.example.tagebuch.controller.MainActivityController;
import com.example.tagebuch.model.pojo.Categoria;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class ReportarPensamientoFragment extends Fragment {



    private RecyclerView recyclerListaCategorias;
    private CategoriasAdapter adapterCategorias;
    private MainActivityController mainActivityController;
    private Button reportarbutton;
    private TextView titulo, descripcion;
    private DividerItemDecoration dividerItemDecoration;
    private LinearLayoutManager layoutManager;



    public ReportarPensamientoFragment() {
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
        View view =inflater.inflate(R.layout.fragment_reportar_pensamiento, container, false);
        mainActivityController = new MainActivityController();
        reportarbutton = view.findViewById(R.id.ReportarButton);
        titulo = view.findViewById(R.id.TituloNuevoPensamientoTextView);
        descripcion = view.findViewById(R.id.DescripcionNuevoPensamientoTextView);

        reportarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               comprobar();

            }
        });
        layoutManager = new LinearLayoutManager(getContext());
        recyclerListaCategorias = view.findViewById(R.id.CategoriasRecycler);
        recyclerListaCategorias.setLayoutManager(layoutManager);
        dividerItemDecoration = new DividerItemDecoration(recyclerListaCategorias.getContext(),layoutManager.getOrientation());

        recyclerListaCategorias.addItemDecoration(dividerItemDecoration);


        List<Categoria> listacategorias;

        listacategorias = mostrarCategorias(mainActivityController);

        adapterCategorias = new CategoriasAdapter(listacategorias,getContext());
        recyclerListaCategorias.setAdapter(adapterCategorias);


        return  view;
    }

    public  void comprobar(){

        mainActivityController.comprobarPensamiento(this,titulo.getText().toString(),descripcion.getText().toString(),adapterCategorias);

    }

    public List<Categoria> mostrarCategorias(MainActivityController controller){

        return controller.obtenerCategorias(getActivity());
    }

    public void  tituloVacio(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Error")
                .setMessage("No puede ingresar un t??tulo vac??o")
                //.setCancelable(false)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    public void limitetitulo(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Error")
                .setMessage("El t??tulo debe contener m??ximo 100 caracteres, por favor verifique y realice el respectivo cambio.")
                //.setCancelable(false)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    public void descripcionVacio(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Error")
                .setMessage("No puede ingresar una descripci??n vac??a")
                //.setCancelable(false)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    public void categoriaNoSeleccionada(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Error")
                .setMessage("Debes seleccionar una categoria")
                //.setCancelable(false)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    public void mostrarpensamientoCorrecto(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Correcto")
                .setMessage("Pensamiento reportado correctamente")
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