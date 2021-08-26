package com.example.tagebuch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.tagebuch.R;
import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.CategoriaRoomDao;
import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.view.fragments.BotonesUsuarioFragment;

public class MainActivity extends AppCompatActivity {

    //Temporal,para crear las categorias.
    private CategoriaRoomDao categoriaRoomDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //Cargando fragmento botones usuario
        Fragment fragment = new BotonesUsuarioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.ChangeFrameLayout,fragment).commit();

//        Categoria categoria1 = new Categoria();
//        categoria1.setTitulo("Pensamiento deductivo");
//        categoria1.setDescripcion("parte de afirmaciones basadas en ideas abstractas y universales para aplicarlas a casos particulares.");
//        categoria1.setColor("#fa0000");
//
//
//        this.categoriaRoomDao = LocalStorage.getLocalStorage(getApplicationContext()).categoriaRoomDao();
//        this.categoriaRoomDao.borrartemporal();


    }
}