package com.opt_daw.actividad92.dtos;

import java.time.Year;

public class MostrarLibroDto {
    private Integer id;
    private String titulo;
    private String autor;
    private String ISBN;
    private Year anioPublicacion;
    private Boolean prestado;

    public MostrarLibroDto() {
    }

    public MostrarLibroDto(Integer id, String titulo, String autor, String ISBN, Year anioPublicacion, Boolean prestado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.anioPublicacion = anioPublicacion;
        this.prestado = prestado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getPrestado() {
        return prestado;
    }

    public void setPrestado(Boolean prestado) {
        this.prestado = prestado;
    }
}

