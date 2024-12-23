package FlotasInteligentes.Controllers;

import FlotasInteligentes.Domain.Conductores;
import FlotasInteligentes.Service.ConductoresService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * El estilo arquitectonico manejado para este proyecto es el de microservicios
 * Ya que este estilo nos ofrece distintas ventajas que son fundamentales para este proyecto
 * Como el hecho de que funciones como seguimiento,rutas,manetimiento y etc se pueden implementar
 * Como servicios independientes.
 * Tambien tenemos que tener en cuenta su facil escalabilidad y flexibilidad
 * Para futuras adiciones y modificaciones.
 * Aparte que usar este estilo nos asegura que si falla un servicio
 * No van a fallar todos.
 */

/**
 * Trade-Offs
 * Beneficios:
 * Con este estilo estamos ganando en escalabilidad horizontal puesto a que si se desea
 * Expandir el proyecto en lugar de mejorar el Hardware de un solo servidor, podemos añadir
 * mas instancias al sistema para que se pueda distribuir la carga de una manera mas eficiente y equitativa.
 *
 * Tambien contamos con mas flexibilidad al momento de personalizar
 * Y añadir mas modulos o servicios al Proyecto
 *
 * Y claro esta la ventaja de que si se cae un modulo o servicio
 * Los demas seguiran funcionando.
 *
 * Desventajas:
 * Lo que si esta claro que es una desventaja es la complejidad al momento de desarrollar esta aplicación
 * Al ser una aplicacion de microservicios en lenguaje Java hay que tener muchas consideraciones y especial cuidado
 * Con los errores o al momento de definir las clases puesto que esto puede llegar a hacer que el codigo no  funcione
 * Por completo y el programa no lo ejecute. Aparte de que un mantenimiento de esto debe de ser agotador y seguramente
 * Caro.
 *
 * Si desearamos llevar este proyecto a la realidad se necesitaria de una inversión muy grande debido
 * a la necesidad de herramientas para ponerlo a funcionar, y de expertos para desarrollarlo y mantenerlo.
 */

/**
 * Consideraciones
 *
 * En este codigo hice lo que pude y como pude
 * ¿A que me refiero con esto?
 * Que faltan muchas de las funciones principales del programa aparte de que este mismo
 * Es muy primitivo, siendo su base de datos una Lista de Java puesto que no encontre la forma
 * De implementar una Base De Datos real con sus conexiones y demas cosas. Mas que nada por falta de
 * Experiencia y tiempo.
 *
 * Tambien queria añadir una funcion de mapa y gps para hacer lo de la ruta inteligente y ubicación
 * Pero no se pudo realizar puesto a que en el pom no me dejaba importar los servicios de GoogleMaps
 * o de algun mapa en general.
 *
 * Por lo que el resultado es un codigo funcional pero con muchas funciones que se podrian mejor
 * O remplazar por otras.
 */

/**
 * Principio SOLID haciendo mencion al S (Single Responsibility)
 * Y es que cada capa tiene una responsabilidad unica
 * En este caso que es un Controller este mismo esta encargado
 * de gestionar las solicitudes HTTP de entradas y salidas.
 */

/**
 * Principio SOLID haciendo mencion al O (OPEN/CLOSED)
 * Y es que los servicios o controladores deben de poder
 * extenderse para permitir nuevos casos de uso peroo no
 * poder modificarse directamente.
 */

@RestController
@RequestMapping("/conductor")
@Validated
public class ConductoresRestController {

    //Instancia de clase
    /**
     * Principio SOLID haciendo mencion al L (Lisko Substitution)
     * Este dice que las subclases deben poder ser intercambiadas
     * por su clase base sin afectar la funcionalidad del sistema
     *
     * Aqui podemos ver que el controlador usa la interfaz ConductoresService
     * que representa a la abstracción en lugar de referirse directamente como
     * ConductoresServiceImpl
     * Haciendo que cualquier clase que implemente el ConductoresService
     * pueda ser sustituida sin afectar el funcionamiento del controlador.
     */
    @Autowired
    private final ConductoresService conductoresService;

    // Inyección del servicio
    /**
     * Principio SOLID haciendo mencion al D (Dependecy Inversion)
     * Este dice que las clases de alto nivel como lo pueden ser los
     * Controladores HTTP no deben depender de clases de bajo nivel
     * Como implementaciones concretas de servicios
     *
     * Aqui podemos ver que el ConductoresController (La clase de alto nivel)
     * no tiene una dependencia directa de ConductoresServiceImpl
     * Lo que permite que el controlador no conozca los detales de bajo nivel
     * De la implementación
     *
     */
    public ConductoresRestController(ConductoresService conductoresService) {
        this.conductoresService = conductoresService;
    }

    /**
     * Obtiene la lista de todos los conductores.
     */
    @GetMapping
    public ResponseEntity<List<Conductores>> getConductores() {
        List<Conductores> conductores = conductoresService.getConductores();
        return ResponseEntity.ok(conductores);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<?> getConductor(@PathVariable @Min(1) int ID) {
        Conductores conductor = conductoresService.getConductor(ID);
        if (conductor != null) {
            return ResponseEntity.ok(conductor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Conductor no encontrado con ID: " + ID);
    }


    @PostMapping
    public ResponseEntity<?> addConductor(@Valid @RequestBody Conductores conductor) {
        String response = conductoresService.addConductor(conductor);
        if (response.startsWith("Ya existe")) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<?> putConductor(@Valid @RequestBody Conductores conductor) {
        String response = conductoresService.updateConductor(conductor);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Conductor no encontrado con ID: " + conductor.getID());
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<?> deleteConductor(@PathVariable @Min(1) int ID) {
        String response = conductoresService.deleteConductor(ID);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Conductor no encontrado con ID: " + ID);
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<?> patchConductor(@Valid @RequestBody Conductores conductor) {

        String response = conductoresService.patchConductor(conductor);

        if (response.startsWith("Conductor modificado satisfactoriamente")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Conductor no encontrado con ID: " + conductor.getID());
    }


}