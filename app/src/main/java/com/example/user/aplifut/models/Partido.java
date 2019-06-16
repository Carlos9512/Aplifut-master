package com.example.user.aplifut.models;

public class Partido {
    private int id;
    private int idEquipo1;
    private String nombreEquipo1;
    private String urlEquipo1;
    private String golesEquipo1;
    private int idEquipo2;
    private String nombreEquipo2;
    private String urlEquipo2;
    private String golesEquipo2;
    private String fecha;
    private String estadio;
    private String estado;

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

    public String getUrlEquipo1() {
        return urlEquipo1;
    }

    public void setUrlEquipo1(String urlEquipo1) {
        this.urlEquipo1 = urlEquipo1;
    }

    public String getUrlEquipo2() {
        return urlEquipo2;
    }

    public void setUrlEquipo2(String urlEquipo2) {
        this.urlEquipo2 = urlEquipo2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEquipo1() {
        return idEquipo1;
    }

    public void setIdEquipo1(int idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public String getNombreEquipo1() {
        return nombreEquipo1;
    }

    public void setNombreEquipo1(String nombreEquipo1) {
        this.nombreEquipo1 = nombreEquipo1;
    }

    public String getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(String golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public String getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(String golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public int getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(int idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public String getNombreEquipo2() {
        return nombreEquipo2;
    }

    public void setNombreEquipo2(String nombreEquipo2) {
        this.nombreEquipo2 = nombreEquipo2;
    }



}
