package com.opt_daw.actividad92.services;

import com.opt_daw.actividad92.dtos.CrearLibroDto;
import com.opt_daw.actividad92.dtos.ErrorDto;
import com.opt_daw.actividad92.dtos.ListarLibroDto;
import com.opt_daw.actividad92.dtos.MostrarLibroDto;
import com.opt_daw.actividad92.models.Libro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LibroService {
    List<Libro> libros = new ArrayList<>();
    private Integer id = 1;

    public Object crearLibro(CrearLibroDto libro) {
        for (Libro lib : libros) {
            if (lib.getISBN().equals(libro.getISBN())){
                return new ErrorDto("ISBN duplicado");
            }
        }

        Libro crearLibro = new Libro(id++, libro.getTitulo(), libro.getAutor(), libro.getISBN(), libro.getAnioPublicacion(),
                false, "Nota interna");

        libros.add(crearLibro);
        return new MostrarLibroDto(crearLibro.getId(), crearLibro.getTitulo(), crearLibro.getAutor()
                , crearLibro.getISBN(), crearLibro.getAnioPublicacion(), crearLibro.getPrestado());
    }

    public List<ListarLibroDto> listarLibros() {
        List<ListarLibroDto> listarLibroDtos = new ArrayList<>();

        for (Libro libro : libros) {
            listarLibroDtos.add(new ListarLibroDto(libro.getId(), libro.getTitulo()));
        }

        return listarLibroDtos;
    }

    public Object mostrarLibroId(Integer id) {
        Libro libroEncontrado = null;
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libroEncontrado = libro;
            }
        }

        if (libroEncontrado != null) {
            return new MostrarLibroDto(libroEncontrado.getId(), libroEncontrado.getTitulo(),
                    libroEncontrado.getAutor(), libroEncontrado.getISBN(), libroEncontrado.getAnioPublicacion(), libroEncontrado.getPrestado());
        } else {
            return new ErrorDto("El libro no existe");
        }
    }

    public Object prestarLibro(Integer id) {
        Libro libroEncontrado = null;
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libroEncontrado = libro;
            }
        }

        if (libroEncontrado == null) {
            return new ErrorDto("El libro no existe");
        }

        if (libroEncontrado.getPrestado()) {
            return new ErrorDto("El libro ya esta prestado");
        }

        libroEncontrado.setPrestado(true);
        return new MostrarLibroDto(libroEncontrado.getId(), libroEncontrado.getTitulo(),
                libroEncontrado.getAutor(), libroEncontrado.getISBN(), libroEncontrado.getAnioPublicacion(), libroEncontrado.getPrestado());
    }

    public Object devolverLibro(Integer id) {
        Libro libroEncontrado = null;
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libroEncontrado = libro;
            }
        }

        if (libroEncontrado == null) {
            return new ErrorDto("El libro no existe");
        }

        if (!libroEncontrado.getPrestado()) {
            return new ErrorDto("El libro no esta prestado");
        }

        libroEncontrado.setPrestado(false);
        return new MostrarLibroDto(libroEncontrado.getId(), libroEncontrado.getTitulo(),
                libroEncontrado.getAutor(), libroEncontrado.getISBN(), libroEncontrado.getAnioPublicacion(), libroEncontrado.getPrestado());
    }

    public Object eliminarLibro(Integer id) {
        Libro libroEncontrado = null;
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libroEncontrado = libro;
            }
        }

        if (libroEncontrado == null) {
            return new ErrorDto("El libro no existe");
        }

        if (libroEncontrado.getPrestado()) {
            return new ErrorDto("El libro esta prestado");
        }

        libros.remove(libroEncontrado);
        return libroEncontrado;
    }
}
