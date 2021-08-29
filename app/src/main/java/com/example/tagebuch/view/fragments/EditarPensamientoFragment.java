package com.example.tagebuch.view.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tagebuch.Adapter.PensamientosAdapter;
import com.example.tagebuch.R;
import com.example.tagebuch.controller.MainActivityController;
import com.example.tagebuch.model.pojo.Pensamiento;
import com.example.tagebuch.view.MainActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;


public class EditarPensamientoFragment extends Fragment {


    Pensamiento pensamiento;
    private TextView fecha,categoria;
    private EditText titulo;
    private EditText descripcion;
    private Button editar;
    private MainActivityController mainActivityController;

    public EditarPensamientoFragment() {
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

        View view = inflater.inflate(R.layout.fragment_editar_pensamiento, container, false);

        fecha = view.findViewById(R.id.FechaEditarPensamientoTextView);
        categoria = view.findViewById(R.id.CategoriaEditarPensamientoTextView);
        titulo = view.findViewById(R.id.TtituloEditarPensamientoEditText);
        descripcion = view.findViewById(R.id.DescripcionEditarPensamientoEditText);
        editar = view.findViewById(R.id.EditarPensamientoButton);
        mainActivityController = new MainActivityController();

        Bundle args = getArguments();;
        pensamiento = (Pensamiento) args.getSerializable("pensamiento");

        fecha.setText(pensamiento.getFecha());
        categoria.setText(pensamiento.getCategoria().getCtitulo());
        titulo.setText(pensamiento.getTitulo());
        descripcion.setText(pensamiento.getDescripcion());

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comprobar(pensamiento);

            }
        });


        return view;
    }

    //Comprobar que el pensamiento editado está correcto.
    public void comprobar(Pensamiento pensamientosineditar){
        Pensamiento pensamientoEditado = new Pensamiento();
        pensamientoEditado.setFecha(pensamiento.getFecha());
        pensamientoEditado.setCategoria(pensamiento.getCategoria());
        pensamientoEditado.setId(pensamiento.getId());
        pensamientoEditado.setTitulo(titulo.getText().toString());
        pensamientoEditado.setDescripcion(descripcion.getText().toString());
        mainActivityController.comprobarEditarPensamiento(this,pensamientoEditado,pensamientosineditar);
    }

    public void  tituloVacio(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Error")
                .setMessage("No puede ingresar un título vacío")
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
                .setMessage("El título debe contener máximo 100 caracteres, por favor verifique y realice el respectivo cambio.")
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
                .setMessage("No puede ingresar una descripción vacía")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    public void EditarPensamientoCorrecto(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Correcto")
                .setMessage("Pensamiento editado correctamente")
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