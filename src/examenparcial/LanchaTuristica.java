package examenparcial;

public class LanchaTuristica extends Vehiculo {

    private String tipoMotor;

    public LanchaTuristica(String patente, String marca, int capacidadPasajeros,
                           int anioFabricacion, String tipoMotor)
            throws VehiculoInvalidoException {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        if (tipoMotor == null) {
            throw new VehiculoInvalidoException("El tipo de motor no puede estar vacío.");
        }
        String motor = tipoMotor.trim().toUpperCase();
        if (!motor.equals("NAFTA") && !motor.equals("DIESEL") && !motor.equals("ELECTRICO")) {
            throw new VehiculoInvalidoException("Tipo de motor inválido. Opciones: NAFTA, DIESEL o ELECTRICO.");
        }
        this.tipoMotor = motor;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    @Override
    public void realizarServicio() {
        System.out.println("Lancha " + getPatente() + ": realizando paseo náutico con motor " + tipoMotor + ".");
    }

    @Override
    public String toString() {
        return "[Lancha] " + super.toString() + " | Motor: " + tipoMotor;
    }
}