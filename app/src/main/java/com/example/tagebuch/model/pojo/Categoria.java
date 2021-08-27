package com.example.tagebuch.model.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "categoria")
public class Categoria implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int cid;
    private String ctitulo;
    private String cdescripcion;
    private String color;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCtitulo() {
        return ctitulo;
    }

    public void setCtitulo(String ctitulo) {
        this.ctitulo = ctitulo;
    }

    public String getCdescripcion() {
        return cdescripcion;
    }

    public void setCdescripcion(String cdescripcion) {
        this.cdescripcion = cdescripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
