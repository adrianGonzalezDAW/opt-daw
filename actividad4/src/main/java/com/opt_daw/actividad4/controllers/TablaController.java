package com.opt_daw.actividad4.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TablaController {
    @GetMapping("/tabla")
    public String mostrarTabla(@RequestParam(name="filas")String filasStr, @RequestParam(name="columnas")String columnasStr) {

        // Controlamos que se pasa un valor numerico o que sea el valor nulo
        Integer filas = 1;
        try {
            filas = Integer.parseInt(filasStr);
        } catch (NumberFormatException e){

        }

        Integer columnas = 1;
        try {
            columnas = Integer.parseInt(columnasStr);
        } catch (NumberFormatException e){

        }

        // Controlamos los limites de filas y columnas del 1-20
        if (filas < 1) {
            filas = 1;
        } else if (filas > 20) {
            filas = 20;
        }

        if (columnas < 1) {
            columnas = 1;
        } else if (columnas > 20) {
            columnas = 20;
        }


        StringBuilder tablaHtml = new StringBuilder("<table border='1'>");

        // Hacemos el encabezado de las columnas
        tablaHtml.append("<thead>");
        tablaHtml.append("<tr>");
        tablaHtml.append("<th></th>");

        for (int i=1; i <= columnas; i++) {
            tablaHtml.append("<th>Columna ").append(i).append("</th>");
        }

        tablaHtml.append("</head>");
        tablaHtml.append("</tr>");

        for (int i=1; i <= filas; i++) {
            tablaHtml.append("<tr>");
            // OPCIONAL: Pongo los nombres de las filas para que se vea mas claro
            tablaHtml.append("<th>Fila " + i + "</th>");
            for (int j=1; j <= columnas; j++){
                tablaHtml.append("<td>");
                tablaHtml.append("Fila " + i + ", Columna " + j);
                tablaHtml.append("</td>");
            }

            tablaHtml.append("</tr>");
        }
        tablaHtml.append("</table>");

        return tablaHtml.toString();
    }
}
