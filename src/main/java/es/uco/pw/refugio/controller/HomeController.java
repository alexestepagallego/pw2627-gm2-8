package es.uco.pw.refugio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import es.uco.pw.refugio.model.domain.Animal;
import es.uco.pw.refugio.model.domain.Trabajador;
import es.uco.pw.refugio.model.repository.AnimalRepository;
import es.uco.pw.refugio.model.repository.TrabajadorRepository;

/**
 * Controlador de la página de inicio.
 */
@Controller
public class HomeController {

    private TrabajadorRepository trabajadorRepository;
    private AnimalRepository animalRepository;

    /**
     * Inyección de dependencias en el constructor y configuración de consultas SQL.
     */
    public HomeController(TrabajadorRepository trabajadorRepository, AnimalRepository animalRepository) {
        this.trabajadorRepository = trabajadorRepository;
        this.animalRepository = animalRepository;

        // Configurar el fichero de consultas SQL para los repositorios
        String sqlQueriesFileName = "./src/main/resources/db/sql.properties";
        this.trabajadorRepository.setSQLQueriesFileName(sqlQueriesFileName);
        this.animalRepository.setSQLQueriesFileName(sqlQueriesFileName);
    }

    /**
     * Atiende las peticiones a la raíz de la aplicación.
     *
     * @return el nombre de la vista de inicio
     */
    @GetMapping("/")
    public String home() {
        System.out.println("\n--- INICIANDO PRUEBAS DE BASE DE DATOS ---");

        // 1. Probar listado de trabajadores
        List<Trabajador> trabajadores = trabajadorRepository.findAllTrabajadores();
        if (trabajadores != null) {
            System.out.println("[HomeController] Número de trabajadores en BD: " + trabajadores.size());
            if (!trabajadores.isEmpty()) {
                System.out.println("   -> Primer trabajador encontrado: " + trabajadores.get(0).getNombre() + " " + trabajadores.get(0).getApellidos());
            }
        } else {
            System.err.println("[HomeController] Error al recuperar trabajadores (lista nula).");
        }

        // 2. Probar listado de animales
        List<Animal> animales = animalRepository.findAllAnimales();
        if (animales != null) {
            System.out.println("[HomeController] Número de animales en BD: " + animales.size());
        } else {
            System.err.println("[HomeController] Error al recuperar animales (lista nula).");
        }

        // 3. Probar búsqueda de un animal concreto por su código (Toby, del insert de prueba)
        String codigoToby = "724098100000001";
        Animal toby = animalRepository.findAnimalByCodigo(codigoToby);
        if (toby != null) {
            System.out.println("[HomeController] Animal con código " + codigoToby + " encontrado:");
            System.out.println("   -> Nombre: " + toby.getNombre());
            System.out.println("   -> Especie: " + toby.getEspecie());
        } else {
            System.out.println("[HomeController] No se encontró ningún animal con el código " + codigoToby);
        }

        System.out.println("--- FIN DE PRUEBAS ---\n");

        return "home";
    }
}
