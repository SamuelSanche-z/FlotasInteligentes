package FlotasInteligentes.Service;

import FlotasInteligentes.Domain.Vehiculos;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

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

@Primary
@Service
public class VehiculosServiceImpl implements VehiculosService {

    private final List<Vehiculos> vehiculos = new CopyOnWriteArrayList<>(); // Manejo seguro para concurrencia

    // Constructor
    public VehiculosServiceImpl() {
        vehiculos.add(new Vehiculos(1, 123, "toyota", "2", "HG123","Muy Lejano",
                "Muy Cerca", false));
        vehiculos.add(new Vehiculos(2, 345, "toyota", "5", "HG345","Avenida 7",
                "Avenida 8", true));
        vehiculos.add(new Vehiculos(5, 789, "chevrolet", "2", "HG789","Medellin",
                "Rio Negro", false));
        vehiculos.add(new Vehiculos(6, 910, "toyota", "3", "HG910","Croacia",
                "Aca", false));
    }

    @Override
    public List<Vehiculos> getVehiculos() {
        // Retornamos una lista inmutable
        return Collections.unmodifiableList(vehiculos);
    }

    @Override
    public Vehiculos getVehiculobyId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        // Buscamos el vehículo por ID, o retornamos null si no existe
        return vehiculos.stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String addVehiculo(Vehiculos vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede estar vacío.");
        }
        if (getVehiculobyId(vehiculo.getId()) != null) {
            return "Ya existe un vehículo con el ID " + vehiculo.getId();
        }
        vehiculos.add(vehiculo);
        return "Vehículo agregado correctamente: " + vehiculo.getMarca() + " " + vehiculo.getModelo();
    }

    @Override
    public String updateVehiculo(Vehiculos vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede estar vacío.");
        }
        for (Vehiculos v : vehiculos) {
            if (v.getId() == vehiculo.getId()) {
                v.setIdConductor(vehiculo.getIdConductor());
                v.setMarca(vehiculo.getMarca());
                v.setModelo(vehiculo.getModelo());
                v.setPlaca(vehiculo.getPlaca());
                v.setLocalizacionAn(vehiculo.getLocalizacionAn());
                v.setLocalizacionAc(vehiculo.getLocalizacionAc());
                v.setMantenimiento(vehiculo.isMantenimiento());
                return "Vehículo actualizado correctamente.";
            }
        }
        return "Vehículo no encontrado con ID: " + vehiculo.getId();
    }

    @Override
    public String deleteVehiculo(int id) {
        Vehiculos vehiculo = getVehiculobyId(id);
        if (vehiculo != null) {
            vehiculos.remove(vehiculo);
            return "Vehículo eliminado correctamente.";
        }
        return "Vehículo no encontrado con ID: " + id;
    }


    /**
     * Codigo Funciona pero ocurre un error desconocido
     * Si esta cambiando la ubicacion Actual a la Ubicacion Anterior
     * Pero en el raw aparece como si no se hubiera encontrado el ID
     * Esto se confirma mirando el GetVehiculos y comparando a como eran
     * Las Locaciones antes
     * @param vehiculo
     * @return
     */
    @Override
    public String patchVehiculo(Vehiculos vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no puede estar vacío.");
        }

        System.out.println("Se busca el vehículo con ID: " + vehiculo.getId());

        for (Vehiculos v : vehiculos) {
            // Comparación de int usando ==
            if (vehiculo.getId() == v.getId()) {
                System.out.println("Vehículo encontrado con ID: " + v.getId());

                // Verificamos si la localización ha cambiado
                if (vehiculo.getLocalizacionAc() != null &&
                        !vehiculo.getLocalizacionAc().equals(v.getLocalizacionAc())) {
                    v.setLocalizacionAn(v.getLocalizacionAc());
                    v.setLocalizacionAc(vehiculo.getLocalizacionAc());

                    // Retornamos el mensaje de éxito
                    return "La flota se ha movido de '" + v.getLocalizacionAn() +
                            "' a '" + v.getLocalizacionAc() + "'.";
                }
                // Si no hay cambios relevantes
                return "No se realizaron cambios en la localización.";
            }
        }

        // Si no se encontró el vehículo
        return "Vehículo no encontrado con ID: " + vehiculo.getId();
    }
}