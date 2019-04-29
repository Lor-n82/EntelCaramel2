package com.example.entelcaramel2.Objetos;

import java.io.Serializable;

public class Caramelo implements Serializable {

    private String envoltorio;
    private String caramelo;

    public Caramelo() {

    }

    public Caramelo(String envoltorio, String caramelo) {
        this.envoltorio = envoltorio;
        this.caramelo = caramelo;
    }

    public String getEnvoltorio() {
        return envoltorio;
    }

    public void setEnvoltorio(String envoltorio) {
        this.envoltorio = envoltorio;
    }

    public String getCaramelo() {
        return caramelo;
    }

    public void setCaramelo(String caramelo) {
        this.caramelo = caramelo;
    }
}
