package examenparcial;

public class ColectivoTuristico extends Vehiculo {

    private int cantidadPisos;

    public ColectivoTuristico(String patente, String marca, int capacidadPasajeros,
                              int anioFabricacion, int cantidadPisos)
            throws VehiculoInvalidoException {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        if (cantidadPisos != 1 && cantidadPisos != 2) {
            throw new VehiculoInvalidoException("La cantidad de pisos debe ser 1 o 2.");
        }
        this.cantidadPisos = cantidadPisos;
    }

    public int getCantidadPisos() {
        return cantidadPisos;
    }

    @Override
    public void realizarServicio() {
        System.out.println("Colectivo " + getPatente() + ": realizando recorrido turístico por la ciudad con "
                + getCapacidadPasajeros() + " pasajeros.");
    }

    @Override
    public String toString() {
        return "[Colectivo] " + super.toString() + " | Pisos: " + cantidadPisos;
    }
}