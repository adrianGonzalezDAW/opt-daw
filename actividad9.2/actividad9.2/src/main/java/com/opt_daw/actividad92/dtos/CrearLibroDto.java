package com.opt_daw.actividad92.dtos;

import java.time.Year;

public class CrearLibroDto {
    private String titulo;
    private String autor;
    private String ISBN;
    private Year anioPublicacion;

    public CrearLibroDto() {
    }

    public CrearLibroDto(String titulo, String autor, String ISBN, Year anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.anioPublicacion = anioPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Year getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Year anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

}
