package com.example.user.aplifut.Room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Marcador {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombreEquipo1;
    private String urlEquipo1;
    private String golesEquipo1;
    private String nombreEquipo2;
    private String urlEquipo2;
    private String golesEquipo2;
    private String fecha;
    private String estadio;
    private String estado;

    public Marcador(String nombreEquipo1, String urlEquipo1, String golesEquipo1, String nombreEquipo2, String urlEquipo2, String golesEquipo2, String fecha, String estadio, String estado) {
        this.nombreEquipo1 = nombreEquipo1;
        this.urlEquipo1 = urlEquipo1;
        this.golesEquipo1 = golesEquipo1;
        this.nombreEquipo2 = nombreEquipo2;
        this.urlEquipo2 = urlEquipo2;
        this.golesEquipo2 = golesEquipo2;
        this.fecha = fecha;
        this.estadio = estadio;
        this.estado = estado;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getNombreEquipo1() {
        return nombreEquipo1;
    }

    public void setNombreEquipo1(String nombreEquipo1) {
        this.nombreEquipo1 = nombreEquipo1;
    }

    public String getUrlEquipo1() {
        return urlEquipo1;
    }

    public void setUrlEquipo1(String urlEquipo1) {
        this.urlEquipo1 = urlEquipo1;
    }

    public String getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(String golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public String getNombreEquipo2() {
        return nombreEquipo2;
    }

    public void setNombreEquipo2(String nombreEquipo2) {
        this.nombreEquipo2 = nombreEquipo2;
    }

    public String getUrlEquipo2() {
        return urlEquipo2;
    }

    public void setUrlEquipo2(String urlEquipo2) {
        this.urlEquipo2 = urlEquipo2;
    }

    public String getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(String golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}