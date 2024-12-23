package FlotasInteligentes.Service;

import FlotasInteligentes.Domain.Vehiculos;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class VehiculosServiceJSONImpl implements VehiculosService {


    @Override
    public List<Vehiculos> getVehiculos() {

        List<Vehiculos> vehiculos;

        try {
            vehiculos = new ObjectMapper()
                    .readValue(this.getClass().getResourceAsStream("/vehiculos.json"),
                            new TypeReference<List<Vehiculos>>() {});

            return vehiculos;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vehiculos getVehiculobyId(int id) {
        return null;
    }

    @Override
    public String addVehiculo(Vehiculos vehiculo) {
        return "";
    }

    @Override
    public String updateVehiculo(Vehiculos vehiculo) {
        return "";
    }

    @Override
    public String deleteVehiculo(int id) {
        return "";
    }

    @Override
    public String patchVehiculo(Vehiculos vehiculo) {
        return "";
    }
}
