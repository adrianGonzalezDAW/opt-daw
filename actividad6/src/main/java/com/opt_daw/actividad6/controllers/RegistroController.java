package com.opt_daw.actividad6.controllers;

import com.opt_daw.actividad6.models.Registro;
import com.opt_daw.actividad6.models.Respuesta;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegistroController {

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    @ResponseBody
    public Respuesta procesarRegistro(@ModelAttribute Registro registro) {
        String nombre = registro.getNombre();
        String apellidos = registro.getApellidos();
        String correo = registro.getCorreo();
        String contraseña = registro.getContraseña();

        Map<String, String> usuarios = new HashMap<>();
        usuarios.put(correo, contraseña);

        return new Respuesta(nombre, apellidos, correo, contraseña);
    }

    public static String generarContraseña() {

    }
}