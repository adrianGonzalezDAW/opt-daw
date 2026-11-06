package com.opt_daw.actividad92.dtos;

public class ListarLibroDto {
    private Integer id;
    private String titulo;

    public ListarLibroDto() {
    }

    public ListarLibroDto(Integer id, String titulo) {
        this.id = id;
        this.titulo = titulo;
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
}
