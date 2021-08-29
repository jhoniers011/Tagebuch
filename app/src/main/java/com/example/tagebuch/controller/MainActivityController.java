package com.example.tagebuch.controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.tagebuch.Adapter.CategoriasAdapter;
import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.CategoriaRoomDao;
import com.example.tagebuch.model.dao.PensamientoRoomDao;
import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.model.pojo.Pensamiento;
import com.example.tagebuch.view.fragments.EditarPensamientoFragment;
import com.example.tagebuch.view.fragments.EliminarPensamientoFragment;
import com.example.tagebuch.view.fragments.ReportarPensamientoFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivityController {

    private CategoriaRoomDao categoriaRoomDao;
    private PensamientoRoomDao pensamientoRoomDao;
    private static PilaTamanoFijo pilaDeshacer = new PilaTamanoFijo(10);
    private static PilaTamanoFijo pilaRehacer = new PilaTamanoFijo(10);

    public List<Categoria> obtenerCategorias(Activity activity){

        this.categoriaRoomDao = LocalStorage.getLocalStorage(activity.getApplicationContext()).categoriaRoomDao();
        return this.categoriaRoomDao.getAll();

    }

    public void comprobarPensamiento(ReportarPensamientoFragment fragment, String titulo, String descripcion,CategoriasAdapter adapter){

        if(comprobarTextoVacio(titulo)){
            fragment.tituloVacio();
            return;
        }

        if (comprobarLimiteTitulo(titulo)){
            fragment.limitetitulo();
            return;
        }

        if (comprobarTextoVacio(descripcion)){
            fragment.descripcionVacio();
            return;
        }


        if (adapter.getPosition() == -1){
            fragment.categoriaNoSeleccionada();
            return;
        }

        //si esta correcto
        guardarPensamiento(fragment.getActivity(),titulo,descripcion,adapter.getSelected());
        fragment.mostrarpensamientoCorrecto();
    }

    public Boolean  comprobarTextoVacio(String texto) {

        if (texto == null || texto.compareTo("") == 0){
            return true;
        }
        return false;

    }

    public Boolean  comprobarLimiteTitulo(String titulo) {

        if (titulo.length() > 100){
            return true;
        }
        return false;

    }

    public void comprobarEditarPensamiento(EditarPensamientoFragment fragment,Pensamiento pensamientoeditado,Pensamiento pensamientosineditar) {

        if (comprobarTextoVacio(pensamientoeditado.getTitulo())) {
            fragment.tituloVacio();
            return;
        }
        if (comprobarLimiteTitulo(pensamientoeditado.getTitulo())) {
            fragment.limitetitulo();
            return;
        }

        if (comprobarTextoVacio(pensamientoeditado.getDescripcion())) {

            fragment.descripcionVacio();
            return;
        }

        //Si es correcto

        actualizarPensamiento(fragment.getActivity(),pensamientoeditado,pensamientosineditar);
        fragment.EditarPensamientoCorrecto();


    }



    public void guardarPensamiento(Activity activity, String titulo, String descripcion, Categoria categoria){

        Pensamiento nuevopensamiento = new Pensamiento();
        nuevopensamiento.setTitulo(titulo);
        nuevopensamiento.setDescripcion(descripcion);
        nuevopensamiento.setCategoria(categoria);
        nuevopensamiento.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(new Date()) );

        InsertarCommand insertar = new InsertarCommand(activity.getApplicationContext(),nuevopensamiento);
        insertar.execute();


        //this.pensamientoRoomDao = LocalStorage.getLocalStorage(activity.getApplicationContext()).pensamientoRoomDao();
        //Pensamiento pensamiento = this.pensamientoRoomDao.ListOne();



        pilaDeshacer.push(insertar);

    }

    public void actualizarPensamiento(Activity activity,Pensamiento pensamientoeditado,Pensamiento pensamientoSinEditar){

        EditarCommand editar = new EditarCommand(activity.getApplicationContext(),pensamientoeditado,pensamientoSinEditar);
        editar.execute();
        //this.pensamientoRoomDao = LocalStorage.getLocalStorage(activity.getApplicationContext()).pensamientoRoomDao();
        //this.pensamientoRoomDao.updateOne(pensamiento);

        //this.pensamientoRoomDao.updateCustom(pensamiento.getTitulo(),pensamiento.getDescripcion(),pensamiento.getId());
        pilaDeshacer.push(editar);


    }

    public void eliminarPensamiento(EliminarPensamientoFragment fragment , Pensamiento pensamiento){
        EliminarCommand eliminar = new EliminarCommand(fragment.getContext(),pensamiento);
        eliminar.execute();

        this.pensamientoRoomDao = LocalStorage.getLocalStorage(fragment.getActivity().getApplicationContext()).pensamientoRoomDao();
        this.pensamientoRoomDao.deleteOne(pensamiento);

        EliminarCommand insertar = new EliminarCommand(fragment.getContext(),pensamiento);
        insertar.execute();
        pilaDeshacer.push(insertar);

        fragment.eliminarPensamientoCorrecto();

    }

    public List<Pensamiento> listarPensamiento(Activity activity){
        this.pensamientoRoomDao = LocalStorage.getLocalStorage(activity.getApplicationContext()).pensamientoRoomDao();
        return this.pensamientoRoomDao.getAll();
    }

    //Cargar las categorias si no están en la base de datos.
    public void cargarCategorias(Context context){
        this.categoriaRoomDao = LocalStorage.getLocalStorage(context).categoriaRoomDao();
        List<Categoria> categorias = this.categoriaRoomDao.getAll();

        if(categorias.size() == 0){

        Categoria categoria1 = new Categoria();
        categoria1.setCtitulo("Pensamiento deductivo ");
        categoria1.setCdescripcion("parte de afirmaciones basadas en ideas abstractas y universales para aplicarlas a casos particulares.");
        categoria1.setColor("#fa0000");
        categoria1.setCid(7);

        Categoria categoria2 = new Categoria();
        categoria2.setCtitulo("Pensamiento inductivo");
        categoria2.setCdescripcion("No parte de afirmaciones generales, sino que se basa en casos particulares y, a partir de ellos, genera ideas generales.");
        categoria2.setColor("#1900fa");

        Categoria categoria3 = new Categoria();
        categoria3.setCtitulo("Pensamiento analítico");
        categoria3.setCdescripcion("Crea piezas de información a partir de una unidad informacional amplia y llega a conclusiones viendo el modo en el que interactúan entre sí estos “fragmentos”.");
        categoria3.setColor("#fae900");

        Categoria categoria4 = new Categoria();
        categoria4.setCtitulo("Pensamiento creativo");
        categoria4.setCdescripcion("Se juega a crear soluciones originales y únicas ante problemas, mediante el cuestionamiento de las normas que en un principio parecen ser evidentes.");
        categoria4.setColor("#00eefa");

        Categoria categoria5 = new Categoria();
        categoria5.setCtitulo("Pensamiento divergente");
        categoria5.setCdescripcion("Se establece una división entre dos o más aspectos de una idea, y se explora las posibilidades de mantener esta “partición”.");
        categoria5.setColor("#15fa00");

        Categoria categoria6 = new Categoria();
        categoria6.setCtitulo("Pensamiento convergente");
        categoria6.setCdescripcion("Se da un proceso por el cual nos damos cuenta de que hay diferentes hechos o realidades que encajan entre sí a pesar de que en un principio parecía que no tenían nada en común.");
        categoria6.setColor("#b300fa");

        this.categoriaRoomDao.insertOne(categoria1);
        this.categoriaRoomDao.insertOne(categoria2);
        this.categoriaRoomDao.insertOne(categoria3);
        this.categoriaRoomDao.insertOne(categoria4);
        this.categoriaRoomDao.insertOne(categoria5);
        this.categoriaRoomDao.insertOne(categoria6);

        }
        return;

    }

    public void deshacer(){

        Command command = (Command) pilaDeshacer.pop();
        pilaRehacer.push(command);
        command.unexecute();
        return;
    }

    public void rehacer(){

        Command command = (Command) pilaRehacer.pop();
        pilaDeshacer.push(command);
        command.execute();
        return;


    }

    public boolean comprobarPilaVaciaDeshacer(){
        if (pilaDeshacer.size() <= 0){
            return false;
        }else {
            return true;
        }
    }

    public boolean comprobarPilaVaciaRehacer(){
        if (pilaRehacer.size() <= 0){
            return false;
        }else {
            return true;
        }
    }


}
