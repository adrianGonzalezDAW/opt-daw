package com.opt_daw.actividad5.models;

public class Pedido {
    Cliente cliente;
    Producto[] productos;

    public Pedido() {
    }

    public Pedido(Cliente cliente, Producto[] productos) {
        this.cliente = cliente;
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }
}
