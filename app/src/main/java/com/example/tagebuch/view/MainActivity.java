package com.example.tagebuch.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tagebuch.R;
import com.example.tagebuch.controller.Command;
import com.example.tagebuch.controller.MainActivityController;
import com.example.tagebuch.controller.PilaTamanoFijo;
import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.CategoriaRoomDao;
import com.example.tagebuch.model.dao.PensamientoRoomDao;
import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.view.fragments.BotonesUsuarioFragment;

public class MainActivity extends AppCompatActivity {

    public static PilaTamanoFijo PilaDeshacer = new PilaTamanoFijo(10);
    public static PilaTamanoFijo PilaRehacer = new PilaTamanoFijo(10);

    private MainActivityController mainActivityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mainActivityController = new MainActivityController();



        mainActivityController.cargarCategorias(getApplicationContext());

        //Cargando fragmento botones usuario
        Fragment fragment = new BotonesUsuarioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.ChangeFrameLayout,fragment).commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menudeshaceryrehacer,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.deshacer:
                if (!mainActivityController.comprobarPilaVaciaDeshacer()){

                    Toast.makeText(this,"No hay nada para deshacer",Toast.LENGTH_SHORT).show();

                }else {
                    deshacer();
                    Toast.makeText(this,"Deshacer",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.rehacer:

                if (!mainActivityController.comprobarPilaVaciaRehacer()){

                    Toast.makeText(this,"No hay nada para rehacer",Toast.LENGTH_SHORT).show();

                }else {
                    rehacer();
                    Toast.makeText(this,"rehacer",Toast.LENGTH_SHORT).show();
                }


                break;


        }

        return super.onOptionsItemSelected(item);
    }

    public void deshacer(){

        mainActivityController.deshacer();

    }

    public void rehacer(){

        mainActivityController.rehacer();

    }



}