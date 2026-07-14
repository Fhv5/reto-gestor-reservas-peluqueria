import java.util.Scanner;

public class Menu {
    public static void mostrarMenu() {
        System.out.println("\n====== Marta Peluquería =====");
        System.out.println("[1] Agendar una reserva");
        System.out.println("[2] Listar reservas del día");
        System.out.println("[3] Cancelar una reserva");
        System.out.println("[4] Ver reporte del día");
        System.out.println("[5] Buscar reserva por cliente");
        System.out.println("[6] Editar una reserva");
        System.out.println("[7] Horas disponibles");
        System.out.println("[8] Servicio más pedido");
        System.out.println("[9] Salir");
    }
    
    public static int leerOpcion(Scanner sc, String errorMsg) {
        while (true) {
            System.out.print("\nSeleccione una opción: ");
            if (sc.hasNextInt()) {
                int valor = sc.nextInt();
                sc.nextLine();
                System.out.println();
                return valor;
            } else {
                System.out.println(errorMsg);
                sc.nextLine();
            }
        }
    }
    
}
