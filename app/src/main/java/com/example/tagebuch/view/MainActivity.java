package com.example.tagebuch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;

import com.example.tagebuch.R;
import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.CategoriaRoomDao;
import com.example.tagebuch.model.dao.PensamientoRoomDao;
import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.view.fragments.BotonesUsuarioFragment;

public class MainActivity extends AppCompatActivity {

    //Temporal,para crear las categorias.
    private CategoriaRoomDao categoriaRoomDao;
    private PensamientoRoomDao pensamientoRoomDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);




        //Cargando fragmento botones usuario
        Fragment fragment = new BotonesUsuarioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.ChangeFrameLayout,fragment).commit();

//        Categoria categoria1 = new Categoria();
//        categoria1.setCtitulo("Pensamiento deductivo actualizado otra vez");
//        categoria1.setCdescripcion("parte de afirmaciones basadas en ideas abstractas y universales para aplicarlas a casos particulares.");
//        categoria1.setColor("#fa0000");
//        categoria1.setCid(7);
//
//        Categoria categoria2 = new Categoria();
//        categoria2.setCtitulo("Pensamiento inductivo");
//        categoria2.setCdescripcion("No parte de afirmaciones generales, sino que se basa en casos particulares y, a partir de ellos, genera ideas generales.");
//        categoria2.setColor("#1900fa");
//
//        Categoria categoria3 = new Categoria();
//        categoria3.setCtitulo("Pensamiento analítico");
//        categoria3.setCdescripcion("Crea piezas de información a partir de una unidad informacional amplia y llega a conclusiones viendo el modo en el que interactúan entre sí estos “fragmentos”.");
//        categoria3.setColor("#fae900");
//
//        Categoria categoria4 = new Categoria();
//        categoria4.setCtitulo("Pensamiento creativo");
//        categoria4.setCdescripcion("Se juega a crear soluciones originales y únicas ante problemas, mediante el cuestionamiento de las normas que en un principio parecen ser evidentes.");
//        categoria4.setColor("#00eefa");
//
//        Categoria categoria5 = new Categoria();
//        categoria5.setCtitulo("Pensamiento divergente");
//        categoria5.setCdescripcion("Se establece una división entre dos o más aspectos de una idea, y se explora las posibilidades de mantener esta “partición”.");
//        categoria5.setColor("#15fa00");
//
//        Categoria categoria6 = new Categoria();
//        categoria6.setCtitulo("Pensamiento convergente");
//        categoria6.setCdescripcion("Se da un proceso por el cual nos damos cuenta de que hay diferentes hechos o realidades que encajan entre sí a pesar de que en un principio parecía que no tenían nada en común.");
//        categoria6.setColor("#b300fa");
//
//        this.categoriaRoomDao = LocalStorage.getLocalStorage(getApplicationContext()).categoriaRoomDao();
//        this.categoriaRoomDao.insertOne(categoria1);
//        this.categoriaRoomDao.insertOne(categoria2);
//        this.categoriaRoomDao.insertOne(categoria3);
//        this.categoriaRoomDao.insertOne(categoria4);
//        this.categoriaRoomDao.insertOne(categoria5);
//        this.categoriaRoomDao.insertOne(categoria6);

        //this.pensamientoRoomDao = LocalStorage.getLocalStorage(getApplicationContext()).pensamientoRoomDao();
        //this.pensamientoRoomDao.deleteall
        //this.categoriaRoomDao = LocalStorage.getLocalStorage(getApplicationContext()).categoriaRoomDao();
        //this.categoriaRoomDao.updateOne(categoria1);

    }
    //comentario de prueba para github


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menudeshaceryrehacer,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }
}