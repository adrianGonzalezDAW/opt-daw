package com.opt_daw.actividad92.controllers;

import com.opt_daw.actividad92.dtos.CrearLibroDto;
import com.opt_daw.actividad92.dtos.ErrorDto;
import com.opt_daw.actividad92.dtos.ListarLibroDto;
import com.opt_daw.actividad92.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @PostMapping
    public ResponseEntity<?> crearLibro(@RequestBody CrearLibroDto libro) {
        Object libroCreado = libroService.crearLibro(libro);
        if (libroCreado instanceof ErrorDto) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(libroCreado);

        }

        return ResponseEntity.status(HttpStatus.OK).body(libroCreado);
    }

    @GetMapping
    public ResponseEntity<List<ListarLibroDto>> listarLibros() {
        List<ListarLibroDto> listarLibroDtos = libroService.listarLibros();

        return ResponseEntity.status(HttpStatus.OK).body(listarLibroDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarLibroId(@PathVariable Integer id){
        Object libroEncontrado = libroService.mostrarLibroId(id);
        if (libroEncontrado instanceof ErrorDto) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(libroEncontrado);

        }

        return ResponseEntity.status(HttpStatus.OK).body(libroEncontrado);
    }

    @PostMapping("/{id}/prestamo")
    public ResponseEntity<?> prestarLibro(@PathVariable Integer id) {
        Object libroEncontrado = libroService.prestarLibro(id);
        if (libroEncontrado instanceof ErrorDto) {
            if (((ErrorDto) libroEncontrado).getMensaje().equals("El libro no existe")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(libroEncontrado);
            }

            if (((ErrorDto) libroEncontrado).getMensaje().equals("El libro ya esta prestado")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(libroEncontrado);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(libroEncontrado);
    }

    @PostMapping("/{id}/devolucion")
    public ResponseEntity<?> devolverLibro(@PathVariable Integer id) {
        Object libroEncontrado = libroService.devolverLibro(id);
        if (libroEncontrado instanceof ErrorDto) {
            if (((ErrorDto) libroEncontrado).getMensaje().equals("El libro no existe")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(libroEncontrado);
            }

            if (((ErrorDto) libroEncontrado).getMensaje().equals("El libro no esta prestado")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(libroEncontrado);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(libroEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Integer id) {
        Object libroEncontrado = libroService.eliminarLibro(id);
        if (libroEncontrado instanceof ErrorDto) {
            if (((ErrorDto) libroEncontrado).getMensaje().equals("El libro no existe")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(libroEncontrado);
            }

            if (((ErrorDto) libroEncontrado).getMensaje().equals("El libro esta prestado")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(libroEncontrado);
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
