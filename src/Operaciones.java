import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Operaciones {
    static final int CUPO_MAX = 2;

    static final int PRECIO_CORTECABELLO = 25000;
    static final int PRECIO_TINTE = 60000;
    static final int PRECIO_MANICURE = 30000;

    static String[] nombres = new String[CUPO_MAX];
    static int[] horas = new int[CUPO_MAX];
    static int[] codsServicio = new int[CUPO_MAX];

    static int nReservas = 0;

    public static void agregar(Scanner sc) {
        if (Validador.cuposLlenos(nReservas)) {
            System.out.println("No quedan cupos disponibles para hoy.");
            return;
        }

        String nombre = Validador.leerNombre(sc, 
            "Ingrese el nombre del cliente (-1 para cancelar): ", 
            "El nombre ingresado no es válido.\n", 
            Validador::nombreValido);

        if (nombre.equals("-1")) return;
        
        int hora = Validador.leerOpcion(sc, 
            "Ingrese la hora de la reserva (8 - 17) (-1 para cancelar): ", 
            "La hora ingresada no es válida o ya está ocupada.\n", 
            Validador::horaValida);

        if (hora == -1) return;
            
        int codServicio = Validador.leerOpcion(sc, 
            "Ingrese el tipo de servicio:\n[1] Corte de cabello ($25.000)\n[2] Tinte ($60.000)\n[3] Manicure ($30.000)\n\nSeleccione una opción (-1 para cancelar): ", 
            "El servicio ingresado no es válido.\n", 
            Validador::servicioValido
        );

        if (codServicio == -1) return;

        nombres[nReservas] = nombre;
        horas[nReservas] = hora;
        codsServicio[nReservas] = codServicio;
        System.out.println("\nReserva agregada exitosamente.");

        nReservas++;
    }

    public static void listar() {
        if (nReservas == 0) {
            System.out.println("Aún no hay reservas");
            return;
        }
        System.out.println("Reservas del dia:");

        Utils.printReservas(i -> true);
    }

    public static void cancelar(Scanner sc) {
        int idReserva = Validador.leerOpcion(sc, 
            "Ingrese el ID de la reserva a eliminar (-1 para cancelar): ", 
            "El código de reserva ingresado no es válido.\n", 
            Validador::idReservaValido);
        
        if (idReserva == -1) return;

        for(int i = idReserva - 1; i < nReservas - 1; i++) {
            nombres[i] = nombres[i + 1];
            horas[i] = horas[i + 1];
            codsServicio[i] = codsServicio[i + 1];
        }

        nombres[nReservas - 1] = null;
        horas[nReservas - 1] = 0;
        codsServicio[nReservas - 1] = 0;

        nReservas--;
        System.out.println("\nReserva cancelada exitosamente.");
    }

    public static void reporte() {
        System.out.println("Resumen del dia:");

        String leftAlignFormat = "| %-16d | %-18s |%n";
        System.out.format("+------------------+--------------------+%n");
        System.out.format("| Total de citas   | Dinero facturado   |%n");
        System.out.format("+------------------+--------------------+%n");
        
        int totalFacturado = 0;

        for (int i : codsServicio) {
            switch (i) {
                case 1: totalFacturado += PRECIO_CORTECABELLO; break;
                case 2: totalFacturado += PRECIO_TINTE; break;
                case 3: totalFacturado += PRECIO_MANICURE; break;
            }
        }

        System.out.printf(leftAlignFormat, nReservas, "$ " + totalFacturado);
        
        System.out.format("+------------------+--------------------+%n");
    }

    public static void buscarPorCliente(Scanner sc) {
        String nombre = Validador.leerNombre(sc, 
            "Ingrese el nombre del cliente (-1 para cancelar): ", 
            "El nombre ingresado no es válido.\n", 
            Validador::nombreValido);

        if (nombre.equals("-1")) return;

        boolean encontrado = false;

        for (int i = 0; i < nReservas; i++) {
            if (nombre.equals(nombres[i])) {
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            System.out.println("No existen reservas para " + nombre + ".");
            return;
        }

        Utils.printReservas(i -> Operaciones.nombres[i].equals(nombre));
    }

    public static String servicioMasPedido() {
        
        String[] servicios = ["Corte de cabello", "Tinte", "Manicure"];
        int[] recurrencias = new int[3];

        for (int i = 0; i < nReservas; i++) {
            switch (codsServicio[i]) {
                case 1: recurrencias[0]++; break;
                case 2: recurrencias[1]++; break;
                case 3: recurrencias[2]++; break;
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < servicios.length; i++) {
            if (recurrencias[i] > recurrencias[maxIndex])
                maxIndex = i;
        }

        return servicios[maxIndex];
    }
    
}
