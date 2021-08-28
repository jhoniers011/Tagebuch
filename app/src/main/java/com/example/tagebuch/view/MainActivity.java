package com.example.tagebuch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;

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

    public void deshacer(){

        // (new UndoCommand(this,controlador,)

    }

    public void rehacer(){

        // ejecutarComando(new redoCommand(this,controador)

    }

    public void ejecutarComando(Command command){
        command.execute();
    }


}