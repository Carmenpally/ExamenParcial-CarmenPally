package examenparcial;

import java.time.LocalDate;

public abstract class Vehiculo {

    private String patente;
    private String marca;
    private int capacidadPasajeros;
    private int anioFabricacion;

    public Vehiculo(String patente, String marca, int capacidadPasajeros, int anioFabricacion)
            throws VehiculoInvalidoException {
        if (patente == null || patente.trim().isEmpty()) {
            throw new VehiculoInvalidoException("La patente no puede estar vacía.");
        }
        if (marca == null || marca.trim().isEmpty()) {
            throw new VehiculoInvalidoException("La marca no puede estar vacía.");
        }
        if (capacidadPasajeros <= 0) {
            throw new VehiculoInvalidoException("La capacidad de pasajeros debe ser mayor a cero.");
        }
        int anioActual = LocalDate.now().getYear();
        if (anioFabricacion < 1990) {
            throw new VehiculoInvalidoException("El año de fabricación no puede ser menor a 1990.");
        }
        if (anioFabricacion > anioActual) {
            throw new VehiculoInvalidoException("El año de fabricación no puede ser mayor a " + anioActual + ".");
        }

        this.patente = patente.trim().toUpperCase();
        this.marca = marca.trim();
        this.capacidadPasajeros = capacidadPasajeros;
        this.anioFabricacion = anioFabricacion;
    }

    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return marca;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public abstract void realizarServicio();

    @Override
    public String toString() {
        return "Patente: " + patente
                + " | Marca: " + marca
                + " | Capacidad: " + capacidadPasajeros
                + " | Año: " + anioFabricacion;
    }
}