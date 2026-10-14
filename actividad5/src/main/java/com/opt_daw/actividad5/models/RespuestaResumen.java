package com.opt_daw.actividad5.models;

public class RespuestaResumen {
    String cliente;
    Integer totalProductos;
    Double precioTotal;

    public RespuestaResumen() {
    }

    public RespuestaResumen(String cliente, Integer totalProductos, Double precioTotal) {
        this.cliente = cliente;
        this.totalProductos = totalProductos;
        this.precioTotal = precioTotal;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(Integer totalProductos) {
        this.totalProductos = totalProductos;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
}
