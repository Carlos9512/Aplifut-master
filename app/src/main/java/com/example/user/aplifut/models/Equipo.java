package com.example.user.aplifut.models;

public class Equipo {
    private int id;
    private String Equipo;
    private String Puntos;
    private String img;
    private String PJ;
    private String G;
    private String E;
    private String P;
    private String GF;
    private String GC;
    private String DG;
    private String Pts;

    public String getPJ() {
        String [] punto= getPuntos().split(" ");
        return punto[0];
    }

    public void setPJ(String PJ) {
        this.PJ = PJ;
    }

    public String getG() {
        String [] punto= getPuntos().split(" ");
        return punto[1];
    }

    public void setG(String g) {
        G = g;
    }

    public String getE() {
        String [] punto= getPuntos().split(" ");
        return punto[2];
    }

    public void setE(String e) {
        E = e;
    }

    public String getP() {
        String [] punto= getPuntos().split(" ");
        return punto[3];
    }

    public void setP(String p) {
        P = p;
    }

    public String getGF() {
        String [] punto= getPuntos().split(" ");
        return punto[4];
    }

    public void setGF(String GF) {
        this.GF = GF;
    }

    public String getGC() {
        String [] punto= getPuntos().split(" ");
        return punto[5];
    }

    public void setGC(String GC) {
        this.GC = GC;
    }

    public String getDG() {
        String [] punto= getPuntos().split(" ");
        return punto[6];
    }

    public void setDG(String DG) {
        this.DG = DG;
    }

    public String getPts() {
        String [] punto= getPuntos().split(" ");
        return punto[7];
    }

    public void setPts(String pts) {
        Pts = pts;
    }

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
