package com.example.iot_lab2.devices;

public class Monitor {
    private String activo;
    private Computadora pc;
    private String marca;
    private int pulgadas;
    private int anho;
    private String modelo;

    public Monitor() {
    }

    public Monitor(String activo, Computadora pc, String marca, int pulgadas, int anho, String modelo) {
        this.activo = activo;
        this.pc = pc;
        this.marca = marca;
        this.pulgadas = pulgadas;
        this.anho = anho;
        this.modelo = modelo;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Computadora getPc() {
        return pc;
    }

    public void setPc(Computadora pc) {
        this.pc = pc;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
