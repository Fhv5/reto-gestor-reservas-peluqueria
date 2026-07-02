import java.util.Scanner;
import java.util.function.Predicate;

public class Validador {
    public static boolean horaValida(int hora) {
        boolean enHorarioLaboral = hora >= 8 && hora <= 17;
        boolean ocupada = false;

        for(int h : Operaciones.horas) {
            if(hora == h) ocupada = true;
        }

        return enHorarioLaboral && !ocupada;
    }

    public static boolean nombreValido(String nombre) {
        return nombre == null || nombre.trim().isEmpty() ? false : true;
    }

    public static boolean servicioValido(int servicio) {
        return servicio < 1 || servicio > 3 ? false : true;
    }

    public static boolean cuposLlenos(int nReservas) {
        return nReservas >= Operaciones.CUPO_MAX ? true : false;
        
    }

    public static boolean idReservaValido(int idReserva) {
        return idReserva > Operaciones.nReservas || idReserva < 1 ? false : true;
    }

    public static String leerNombre(Scanner sc, String mensaje, String mensajeError, Predicate<String> validador) {
        String nombre;
        boolean esValido;
        do {
            System.out.print(mensaje);
            nombre = sc.nextLine();
            esValido = validador.test(nombre) || nombre.equals("-1");
            if (!esValido) {
                System.out.println(mensajeError);
            }
        } while (!esValido);
        return nombre;
    }

    public static int leerOpcion(Scanner sc, String mensaje, String mensajeError, Predicate<Integer> validador) {
        int opcion = 0;
        boolean esValido = false;
        do {
            try {
                System.out.print(mensaje);
                opcion = Integer.parseInt(sc.nextLine());
                esValido = validador.test(opcion) || opcion == -1;
                if (!esValido) {
                    System.out.println(mensajeError);
                }
            } catch (NumberFormatException e) {
                System.out.println("\nEl valor ingresado no es válido.\n");
            }
        } while (!esValido);
        return opcion;
    }

}
