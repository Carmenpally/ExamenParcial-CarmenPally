package examenparcial;

import java.util.ArrayList;
import java.util.Scanner;

public class ExamenParcial {

    private static AgenciaTurismo agencia = new AgenciaTurismo();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if (leerSiNo("¿Desea cargar los datos de prueba? (S/N): ")) {
            agencia.cargarDatosDePrueba();
            System.out.println("Datos de prueba cargados.");
        }

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elija una opción: ");
            ejecutarOpcion(opcion);
        } while (opcion != 8);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== TRANSPORTE TURÍSTICO =====");
        System.out.println("1. Agregar vehículo");
        System.out.println("2. Mostrar todos los vehículos");
        System.out.println("3. Realizar servicios turísticos");
        System.out.println("4. Buscar vehículo por patente");
        System.out.println("5. Mostrar vehículos con capacidad mayor a un valor");
        System.out.println("6. Mostrar vehículos por año de fabricación (descendente)");
        System.out.println("7. Mostrar vehículos por capacidad de pasajeros (descendente)");
        System.out.println("8. Salir");
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                altaVehiculo();
                break;
            case 2:
                agencia.mostrarTodos();
                break;
            case 3:
                agencia.realizarServicios();
                break;
            case 4:
                buscarVehiculo();
                break;
            case 5:
                int valor = leerEntero("Ingrese la capacidad mínima: ");
                agencia.mostrarConCapacidadMayor(valor);
                break;
            case 6:
                agencia.mostrarLista(agencia.ordenarPorAnioDesc());
                break;
            case 7:
                agencia.mostrarLista(agencia.ordenarPorCapacidadDesc());
                break;
            case 8:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción inválida. Elija un número entre 1 y 8.");
        }
    }

    private static void altaVehiculo() {
        boolean reintentar = true;

        while (reintentar) {
            System.out.println("Tipo de vehículo: 1) Colectivo  2) Van  3) Lancha  0) Cancelar");
            int tipo = leerEntero("Tipo: ");

            if (tipo == 0) {
                System.out.println("Alta cancelada.");
                return;
            }
            if (tipo < 1 || tipo > 3) {
                System.out.println("Tipo inválido. Elija 0, 1, 2 o 3.");
                continue;
            }

            String patente = leerTexto("Patente: ");
            String marca = leerTexto("Marca: ");
            int capacidad = leerEntero("Capacidad de pasajeros: ");
            int anio = leerEntero("Año de fabricación: ");

            // Dato específico según el tipo
            int pisos = 0;
            boolean aire = false;
            String motor = "";
            if (tipo == 1) {
                pisos = leerEntero("Cantidad de pisos (1 o 2): ");
            } else if (tipo == 2) {
                aire = leerSiNo("¿Tiene aire acondicionado? (S/N): ");
            } else {
                motor = leerTexto("Tipo de motor (NAFTA, DIESEL o ELECTRICO): ");
            }

            try {
                Vehiculo v;
                if (tipo == 1) {
                    v = new ColectivoTuristico(patente, marca, capacidad, anio, pisos);
                } else if (tipo == 2) {
                    v = new VansEjecutiva(patente, marca, capacidad, anio, aire);
                } else {
                    v = new LanchaTuristica(patente, marca, capacidad, anio, motor);
                }
                agencia.agregarVehiculo(v);
                System.out.println("Vehículo agregado correctamente.");
                reintentar = false;
            } catch (VehiculoInvalidoException e) {
                System.out.println("Error: " + e.getMessage());
                reintentar = leerSiNo("¿Desea volver a intentarlo? (S/N): ");
            }
        }
    }

    private static void buscarVehiculo() {
        String patente = leerTexto("Ingrese la patente a buscar: ");
        if (patente.trim().isEmpty()) {
            System.out.println("Debe ingresar una patente.");
            return;
        }
        Vehiculo v = agencia.buscarPorPatente(patente);
        if (v == null) {
            System.out.println("No se encontró ningún vehículo con la patente " + patente.trim().toUpperCase() + ".");
        } else {
            System.out.println("Vehículo encontrado:");
            System.out.println(v);
        }
    }

    // Se repite hasta que el usuario escriba un entero válido
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida: ingrese un número entero.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    private static boolean leerSiNo(String mensaje) {
        while (true) {
            String r = leerTexto(mensaje).trim().toUpperCase();
            if (r.equals("S") || r.equals("SI")) {
                return true;
            }
            if (r.equals("N") || r.equals("NO")) {
                return false;
            }
            System.out.println("Responda S o N.");
        }
    }
}