package org.example.websocket.model;

public class Cidade {
    private String nome;
    private double lat;
    private double lon;

    public Cidade(String nome, double lat, double lon) {
        this.nome = nome;
        this.lat = lat;
        this.lon = lon;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
