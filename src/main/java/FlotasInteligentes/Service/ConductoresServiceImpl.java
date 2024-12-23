package FlotasInteligentes.Service;

import FlotasInteligentes.Domain.Conductores;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Principio SOLID haciendo mencion al S (Single Responsibility)
 * Y es que cada capa tiene una responsabilidad unica
 * En este caso que es un Service este mismo contiene
 * toda la logica de negocio.
 */

/**
 * Principio SOLID haciendo mencion al O (OPEN/CLOSED)
 * Y es que los servicios o controladores deben de poder
 * extenderse para permitir nuevos casos de uso peroo no
 * poder modificarse directamente.
 */
@Service
public class ConductoresServiceImpl implements ConductoresService {

    @Override
    public Conductores getConductor(int id) {
        return getConductorById(id);
    }

    private final List<Conductores> conductores = new CopyOnWriteArrayList<>(); // Manejo seguro para concurrencia

    // Constructor
    public ConductoresServiceImpl() {
        conductores.add(new Conductores(123, "Gerardo Lopez", "Gerardo@gmail.com", 123));
        conductores.add(new Conductores(345, "Carlos Perez", "Carlos@gmail.com", 345));
        conductores.add(new Conductores(567, "Laura Sanchez", "Laura@gmail.com", 567));
        conductores.add(new Conductores(789, "Carlos Martinez", "Carlos@gmail.com", 789));
    }

    @Override
    public List<Conductores> getConductores() {
        return Collections.unmodifiableList(conductores); // Retornamos lista inmutable
    }

    @Override
    public Conductores getConductorById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        return conductores.stream()
                .filter(c -> c.getID() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String addConductor(Conductores conductor) {
        if (conductor == null) {
            throw new IllegalArgumentException("El conductor no puede estar vacio.");
        }
        if (getConductorById(conductor.getID()) != null) {
            return "Ya existe un conductor con el ID: " + conductor.getID();
        }
        conductores.add(conductor);
        return "El conductor fue creado satisfactoriamente: " + conductor.getName();
    }

    @Override
    public String updateConductor(Conductores conductor) {
        if (conductor == null) {
            throw new IllegalArgumentException("El conductor no puede estar vacio.");
        }
        for (Conductores c : conductores) {
            if (c.getID() == conductor.getID()) {
                c.setName(conductor.getName());
                c.setEmail(conductor.getEmail());
                c.setIdFlotas(conductor.getIdFlotas());
                return "Conductor actualizado exitosamente.";
            }
        }
        return "Conductor no encontrado con ID: " + conductor.getID();
    }

    @Override
    public String deleteConductor(int id) {
        Conductores conductor = getConductorById(id);
        if (conductor != null) {
            conductores.remove(conductor);
            return "Conductor eliminado correctamente.";
        }
        return "Conductor no encontrado con ID: " + id;
    }

    @Override
    public String patchConductor(Conductores conductor) {
        if (conductor == null) {
            throw new IllegalArgumentException("El conductor no puede ser nulo.");
        }
        for (Conductores c : conductores) {

            if (c.getID() == conductor.getID()) {
                if (conductor.getName() != null) {
                    c.setName(conductor.getName());
                }
                if (conductor.getEmail() != null) {
                    c.setEmail(conductor.getEmail());
                }
                if (conductor.getIdFlotas() > 0) { // Validación de valores positivos
                    c.setIdFlotas(conductor.getIdFlotas());
                }
                return "Conductor modificado satisfactoriamente: " + c.getName();
            }
        }
        return "Conductor no encontrado con ID: " + conductor.getID();
    }
}