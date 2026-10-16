package com.opt_daw.actividad6.controllers;

import com.opt_daw.actividad6.models.Inicio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String mostrarRegistro() {
        return "inicio";
    }

    @PostMapping("/login")
    @ResponseBody
    public String procesarLogin(@ModelAttribute Inicio inicio) {
        String correo = inicio.getCorreo();
        String contraseña = inicio.getContraseña();

        // Comprobamos si existe el correo en el map
        if (!RegistroController.usuarios.containsKey(correo)) {
            return "Error el usuario no existe";
        }

        // Si el correo esta en el map validamos la contraseña
        String contraseñaMap = RegistroController.usuarios.get(correo);

        if (!contraseña.equals(contraseñaMap)) {
            return "Error contraseña incorrecta";
        }

        return "Inicio de sesión correcto. Bienvenido " + correo;
    }
}
