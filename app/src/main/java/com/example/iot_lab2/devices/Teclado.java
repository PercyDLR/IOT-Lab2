package com.example.iot_lab2.devices;

public class Teclado {

    private String activo;
    private Computadora pc;
    private String marca;
    private int anho;
    private String idioma;
    private String modelo;

    public Teclado(String activo, Computadora pc, String marca, int anho, String idioma, String modelo) {
        this.activo = activo;
        this.pc = pc;
        this.marca = marca;
        this.anho = anho;
        this.idioma = idioma;
        this.modelo = modelo;
    }

    public Teclado() {
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

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
