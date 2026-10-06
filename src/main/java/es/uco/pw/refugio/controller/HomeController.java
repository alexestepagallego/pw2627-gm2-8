package es.uco.pw.refugio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador de la página de inicio.
 */
@Controller
public class HomeController {

    /**
     * Atiende las peticiones a la raíz de la aplicación.
     *
     * @return el nombre de la vista de inicio
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
