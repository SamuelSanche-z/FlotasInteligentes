package FlotasInteligentes.Service;

import FlotasInteligentes.Domain.Vehiculos;

import java.util.List;

/**
 * Principio SOLID haciendo mencion al principio I (Interface Segregation Principle)
 * En este codigo se puede evidenciar ese principia puesto que la interfaz
 * VehiculosService esta enfocada a unicamente manejar la logica de negocio
 * Relacioanada con los vehiculos... Osea
 * Los metodos que estan definidos en esta interfaz estan agruoadis
 * De maneranlogica y son especificos de una unica responsabilidad.
 */

public interface VehiculosService {

    List<Vehiculos> getVehiculos();

    Vehiculos getVehiculobyId(int id);

    String addVehiculo(Vehiculos vehiculo);

    String updateVehiculo(Vehiculos vehiculo);

    String deleteVehiculo(int id);

    String patchVehiculo(Vehiculos vehiculo);

}
