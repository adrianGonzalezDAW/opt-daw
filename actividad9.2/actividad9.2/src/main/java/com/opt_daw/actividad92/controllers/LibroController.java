package com.opt_daw.actividad92.controllers;

import com.opt_daw.actividad92.dtos.CrearLibroDto;
import com.opt_daw.actividad92.dtos.ErrorDto;
import com.opt_daw.actividad92.dtos.ListarLibroDto;
import com.opt_daw.actividad92.dtos.MostrarLibroDto;
import com.opt_daw.actividad92.models.Libro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {
    List<Libro> libros = new ArrayList<>();
    private Integer id = 1;

    @PostMapping
    public ResponseEntity<?> crearLibro(@RequestBody CrearLibroDto libro) {
        // Controlamos si el ISBN ya existe en la lista
        for (Libro lib : libros) {
            if (lib.getISBN().equals(libro.getISBN())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto("ISBN duplicado"));
            }
        }

        Libro crearLibro = new Libro(id++, libro.getTitulo(), libro.getAutor(), libro.getISBN(), libro.getAnioPublicacion(),
                false, "Nota interna");

        libros.add(crearLibro);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MostrarLibroDto(crearLibro.getId(), crearLibro.getTitulo(), crearLibro.getAutor()
                , crearLibro.getISBN(), crearLibro.getAnioPublicacion(), crearLibro.getPrestado()));
    }

    @GetMapping
    public ResponseEntity<List<ListarLibroDto>> listarLibros() {
        List<ListarLibroDto> listarLibroDtos = new ArrayList<>();

        for (Libro libro : libros) {
            listarLibroDtos.add(new ListarLibroDto(libro.getId(), libro.getTitulo()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(listarLibroDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarLibroId(@PathVariable Integer id){
        Libro libroEncontrado = null;
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libroEncontrado = libro;
            }
        }

        if (libroEncontrado != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new MostrarLibroDto(libroEncontrado.getId(), libroEncontrado.getTitulo(),
                    libroEncontrado.getAutor(), libroEncontrado.getISBN(), libroEncontrado.getAnioPublicacion(), libroEncontrado.getPrestado()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("El libro no existe"));
        }
    }

    @PostMapping("/{id}/prestamo")
    public ResponseEntity<?> prestarLibro(@PathVariable Integer id) {
        Libro libroEncontrado = null;
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libroEncontrado = libro;
            }
        }

        if (libroEncontrado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("El libro no existe"));
        }

        if (libroEncontrado.getPrestado()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto("El libro ya esta prestado"));
        }

        libroEncontrado.setPrestado(true);
        return ResponseEntity.status(HttpStatus.OK).body(new MostrarLibroDto(libroEncontrado.getId(), libroEncontrado.getTitulo(),
            libroEncontrado.getAutor(), libroEncontrado.getISBN(), libroEncontrado.getAnioPublicacion(), libroEncontrado.getPrestado()));
    }

    @PostMapping("/{id}/devolucion")
    public ResponseEntity<?> devolverLibro(@PathVariable Integer id) {
        Libro libroEncontrado = null;
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libroEncontrado = libro;
            }
        }

        if (libroEncontrado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("El libro no existe"));
        }

        if (!libroEncontrado.getPrestado()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto("El libro no esta prestado"));
        }

        libroEncontrado.setPrestado(false);
        return ResponseEntity.status(HttpStatus.OK).body(new MostrarLibroDto(libroEncontrado.getId(), libroEncontrado.getTitulo(),
                libroEncontrado.getAutor(), libroEncontrado.getISBN(), libroEncontrado.getAnioPublicacion(), libroEncontrado.getPrestado()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Integer id) {
        Libro libroEncontrado = null;
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libroEncontrado = libro;
            }
        }

        if (libroEncontrado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("El libro no existe"));
        }

        if (libroEncontrado.getPrestado()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto("El libro esta prestado"));
        }

        libros.remove(libroEncontrado);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
