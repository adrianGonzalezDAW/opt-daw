package com.opt_daw.actividad6.controllers;

import com.opt_daw.actividad6.models.Registro;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistroController {

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }


    @PostMapping("/registro")
    @ResponseBody
    public String procesarRegistro(@ModelAttribute Registro registro) {
        return String.format("Formulario recibido. Nombre: %s; Apellidos: %s; Correo: %s; Contraseña: %s",
                registro.getNombre(), registro.getApellidos(), registro.getCorreo(), registro.getContraseña());
    }
}
