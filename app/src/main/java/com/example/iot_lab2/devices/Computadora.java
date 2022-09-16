package com.example.iot_lab2.devices;

import java.io.Serializable;

public class Computadora implements Serializable {
    private String activo;
    private String marca;
    private int anho;
    private String cpu;

    public Computadora(String activo, String marca, int anho, String cpu) {
        this.activo = activo;
        this.marca = marca;
        this.anho = anho;
        this.cpu = cpu;
    }
    public Computadora(){

    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }
}
