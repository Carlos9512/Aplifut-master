package com.example.user.aplifut.models;

public class Equipo {
    private int id;
    private String Equipo;
    private String Puntos;
    private String img;

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipo() {
        return Equipo;
    }

    public void setEquipo(String equipo) {
        Equipo = equipo;
    }

    public String getPuntos() {
        return Puntos;
    }

    public void setPuntos(String puntos) {
        Puntos = puntos;
    }

    public String getImg() {
        return img;
    }
}
