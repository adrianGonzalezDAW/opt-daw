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
import java.util.Random;

@Controller
public class RegistroController {

    static Map<String, String> usuarios = new HashMap<>();

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    @ResponseBody
    public Object procesarRegistro(@ModelAttribute Registro registro) {
        String nombre = registro.getNombre();
        String apellidos = registro.getApellidos();
        String correo = registro.getCorreo();
        String contraseña = registro.getContraseña();

        // Validaciones obligatorias
        if (nombre == null || nombre.trim().isEmpty() || correo == null || correo.trim().isEmpty()) {
            return "Error: El nombre y el correo electrónico son obligatorios.";
        }

        boolean contraseñaGenerada = false;
        // Validacion de contraseña
        if (contraseña == null || contraseña.trim().isEmpty()) {
            Random random = new Random();
            int numeroRandom = random.nextInt(1000000); // Desde 0 a 999999
            contraseña = String.format("%06d", numeroRandom);

            registro.setContraseña(contraseña);
            contraseñaGenerada = true;
        }

        if (apellidos == null) {
            apellidos = "";
            registro.setApellidos(apellidos);
        }

        usuarios.put(correo, contraseña);

        // Controlamos si la contraseña no es generada la pasamos a null para no filtrarla
        String contraseñaMostrar;

        if (!contraseñaGenerada) {
            contraseña = null;
        }

        return new Respuesta(nombre, apellidos, correo, contraseña);
    }
}