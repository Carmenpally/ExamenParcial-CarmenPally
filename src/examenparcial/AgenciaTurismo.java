package examenparcial;

import java.util.ArrayList;

public class AgenciaTurismo {

    private ArrayList<Vehiculo> vehiculos;

    public AgenciaTurismo() {
        vehiculos = new ArrayList<>();
    }

    
    public void agregarVehiculo(Vehiculo v) throws VehiculoInvalidoException {
        if (v == null) {
            throw new VehiculoInvalidoException("No se puede agregar un vehículo vacío.");
        }
        if (existePatente(v.getPatente())) {
            throw new VehiculoInvalidoException("Ya existe un vehículo con la patente " + v.getPatente() + ".");
        }
        vehiculos.add(v);
    }

    public boolean existePatente(String patente) {
        return buscarPorPatente(patente) != null;
    }

    
    public Vehiculo buscarPorPatente(String patente) {
        if (patente == null) {
            return null;
        }
        String buscada = patente.trim();
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getPatente().equalsIgnoreCase(buscada)) {
                return vehiculos.get(i);
            }
        }
        return null;
    }

    // Opción 2
    public void mostrarTodos() {
        mostrarLista(vehiculos);
    }

    // Opción 3
    public void realizarServicios() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos cargados para realizar servicios.");
            return;
        }
        for (int i = 0; i < vehiculos.size(); i++) {
            vehiculos.get(i).realizarServicio();
        }
    }

    // Opción 5
    public void mostrarConCapacidadMayor(int valor) {
        ArrayList<Vehiculo> filtrados = new ArrayList<>();
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getCapacidadPasajeros() > valor) {
                filtrados.add(vehiculos.get(i));
            }
        }
        System.out.println("Vehículos con capacidad mayor a " + valor + ":");
        mostrarLista(filtrados);
    }

    // Opción 6
    public ArrayList<Vehiculo> ordenarPorAnioDesc() {
        ArrayList<Vehiculo> copia = new ArrayList<>(vehiculos);
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).getAnioFabricacion() < copia.get(j + 1).getAnioFabricacion()) {
                    Vehiculo aux = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, aux);
                }
            }
        }
        return copia;
    }

    // Opción 7
    public ArrayList<Vehiculo> ordenarPorCapacidadDesc() {
        ArrayList<Vehiculo> copia = new ArrayList<>(vehiculos);
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).getCapacidadPasajeros() < copia.get(j + 1).getCapacidadPasajeros()) {
                    Vehiculo aux = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, aux);
                }
            }
        }
        return copia;
    }

    // Otro
    public void mostrarLista(ArrayList<Vehiculo> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay vehículos para mostrar.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    // Carga los 6 datos de prueba del enunciado
    public void cargarDatosDePrueba() {
        try {
            agregarVehiculo(new ColectivoTuristico("AA123BB", "Mercedes Benz", 45, 2020, 2));
            agregarVehiculo(new ColectivoTuristico("AB456CD", "Scania", 50, 2022, 1));
            agregarVehiculo(new VansEjecutiva("AC789EF", "Renault", 15, 2021, true));
            agregarVehiculo(new VansEjecutiva("AD321GH", "Toyota", 12, 2019, false));
            agregarVehiculo(new LanchaTuristica("LAN001", "Yamaha", 8, 2023, "NAFTA"));
            agregarVehiculo(new LanchaTuristica("LAN002", "Mercury", 10, 2022, "ELECTRICO"));
        } catch (VehiculoInvalidoException e) {
            System.out.println("Error al cargar datos de prueba: " + e.getMessage());
        }
    }
}