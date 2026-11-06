package com.opt_daw.actividad92.dtos;

public class ErrorDto {
    private String mensaje;

    public ErrorDto() {
    }

    public ErrorDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
