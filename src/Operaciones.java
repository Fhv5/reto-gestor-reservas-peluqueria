import java.util.Scanner;

public class Operaciones {
    static final int CUPO_MAX = 10;

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

        String leftAlignFormat = "| %-4d | %-24s | %-6s | %-18s|%n";
        System.out.format("+------+--------------------------+--------+-------------------+%n");
        System.out.format("| ID   | Nombre                   | Hora   | Servicio          |%n");
        System.out.format("+------+--------------------------+--------+-------------------+%n");
        
        for (int i = 0; i < nReservas; i++) {
            System.out.printf(leftAlignFormat, i + 1, nombres[i], horas[i] + ":00", servicioMapping(codsServicio[i]));
        }
        System.out.format("+------+--------------------------+--------+-------------------+%n");
    }

    private static String servicioMapping(int codServicio) {
        String retVal = "";

        switch (codServicio) {
            case 1: retVal = "Corte de Cabello"; break;
            case 2: retVal = "Tinte"; break;
            case 3: retVal = "Manicure"; break;            
        }

        return retVal;
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
    
}
