package com.opt_daw.actividad5.controllers;

import com.opt_daw.actividad5.models.Pedido;
import com.opt_daw.actividad5.models.Producto;
import com.opt_daw.actividad5.models.RespuestaResumen;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    @PostMapping("/pedido")
    public RespuestaResumen recibirPedido(@RequestBody Pedido pedido){
        String nombreCliente = pedido.getCliente().getNombre();
        Integer totalProductos= 0;
        Double precioTotal = 0.0;

        for(Producto producto : pedido.getProductos()) {
            totalProductos += producto.getCantidad();
            precioTotal += (producto.getCantidad() * producto.getPrecioUnitario());
        }

        return new RespuestaResumen(nombreCliente, totalProductos, precioTotal);
    }
}
