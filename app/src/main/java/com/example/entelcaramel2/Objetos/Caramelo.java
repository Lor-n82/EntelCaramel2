package com.example.entelcaramel2.Objetos;

import java.io.Serializable;

public class Caramelo implements Serializable {

    private int envoltorio;
    private int sabor;

    public Caramelo() {

    }

    public Caramelo(int envoltorio, int sabor) {
        this.envoltorio = envoltorio;
        this.sabor = sabor;
    }

    public int getSabor() {
        return sabor;
    }

    public void setSabor(int sabor) {
        this.sabor = sabor;
    }

    public int getEnvoltorio() {
        return envoltorio;
    }

    public void setEnvoltorio(int envoltorio) {
        this.envoltorio = envoltorio;
    }

}
