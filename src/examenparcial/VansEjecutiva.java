package examenparcial;

public class VansEjecutiva extends Vehiculo {

    private boolean aireAcondicionado;

    public VansEjecutiva(String patente, String marca, int capacidadPasajeros,
                        int anioFabricacion, boolean aireAcondicionado)
            throws VehiculoInvalidoException {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    @Override
    public void realizarServicio() {
        System.out.println("Van " + getPatente() + ": realizando traslado ejecutivo "
                + (aireAcondicionado ? "con" : "sin") + " aire acondicionado.");
    }

    @Override
    public String toString() {
        return "[Van] " + super.toString() + " | Aire: " + (aireAcondicionado ? "Sí" : "No");
    }
}