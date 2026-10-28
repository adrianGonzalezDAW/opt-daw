package com.opt_daw.actividad91.controllers;

import com.opt_daw.actividad91.models.Producto;
import com.opt_daw.actividad91.models.Respuesta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductosController {
    List<Producto> productos = new ArrayList<>(List.of(
            new Producto(1, "Coche", 1000.0)
    ));

    @GetMapping("/productos")
    public ResponseEntity<?> listarProductos() {
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Respuesta("No hay productos"));
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/productos/buscar")
    public ResponseEntity<?> buscarPorId(@RequestParam Integer id) {
        Optional<Producto> resultado = Optional.empty();

        for (Producto producto : productos) {
            if (producto.getId() == id) {
                resultado = Optional.of(producto);
                break;
            }
        }

        Producto encontrado = resultado.orElse(null);

        if (encontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Respuesta("No hay productos con este id"));
        }

        return ResponseEntity.ok(encontrado);
    }

    @GetMapping("/productos/resumen")
    public ResponseEntity<?> resumen() {
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            int numProductos = productos.size();
            String mensaje = "Hay " + numProductos;

            if (numProductos == 1) {
                mensaje += " producto";
            } else {
                mensaje += " productos";
            }

            return ResponseEntity.ok(new Respuesta(mensaje));
        }
    }

    @PostMapping("/productos/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody Producto producto) {
        if (productos.size() == 5) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Respuesta("El catálogo está lleno"));
        }

        productos.add(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }
}
