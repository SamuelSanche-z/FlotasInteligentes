package FlotasInteligentes.Service;

import FlotasInteligentes.Domain.Conductores;

import java.util.List;

/**
 * Principio SOLID haciendo mencion al principio I (Interface Segregation Principle)
 * En este codigo se puede evidenciar ese principia puesto que la interfaz
 * ConductoresService esta enfocada a unicamente manejar la logica de negocio
 * Relacioanada con los Conductores... Osea
 * Los metodos que estan definidos en esta interfaz estan agruoadis
 * De maneranlogica y son especificos de una unica responsabilidad.
 */

public interface ConductoresService {

    List<Conductores> getConductores();

    Conductores getConductor(int id);

    Conductores getConductorById(int id);

    String addConductor(Conductores conductor);

    String updateConductor(Conductores conductor);

    String deleteConductor(int id);

    String patchConductor(Conductores conductor);
}