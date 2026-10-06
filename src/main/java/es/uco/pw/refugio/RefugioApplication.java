package es.uco.pw.refugio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación de gestión del refugio de animales.
 * Arranca Spring y el servidor web embebido.
 */
@SpringBootApplication
public class RefugioApplication {

    /**
     * Lanza la aplicación.
     *
     * @param args los argumentos de la línea de órdenes
     */
    public static void main(String[] args) {
        SpringApplication.run(RefugioApplication.class, args);
    }
}
