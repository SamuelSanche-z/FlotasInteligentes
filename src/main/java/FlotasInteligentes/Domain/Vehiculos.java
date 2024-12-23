package FlotasInteligentes.Domain;

public class Vehiculos {

    private Integer id;
    private Integer idConductor;
    private String marca;
    private String modelo;
    private String placa;
    private String localizacionAn;
    private String localizacionAc;
    private boolean mantenimiento;

    public Vehiculos() {

    }

    public Vehiculos(Integer id, Integer idConductor, String marca, String modelo, String placa, String localizacionAn,
            String localizacionAc, boolean mantenimiento) {
        this.id = id;
        this.idConductor = idConductor;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.localizacionAn = localizacionAn;
        this.localizacionAc = localizacionAc;
        this.mantenimiento = mantenimiento;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getLocalizacionAn() {return localizacionAn;}

    public void setLocalizacionAn(String localizacionAn) {this.localizacionAn = localizacionAn;}

    public String getLocalizacionAc() {return localizacionAc;}

    public void setLocalizacionAc(String localizacionAc) {this.localizacionAc = localizacionAc;}

    public boolean isMantenimiento() {
        return mantenimiento;
    }


    public void setMantenimiento(boolean mantenimiento) {
        this.mantenimiento = mantenimiento;
    }


}
