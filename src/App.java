import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        int op;
        final String ERROR_INPUT_MSG = "La opción ingresada no es válida. Intente nuevamente.";

        do {
            Menu.mostrarMenu();
            op = Menu.leerOpcion(sc, ERROR_INPUT_MSG);
            switch (op) {
                case 1: Operaciones.agregar(sc);; break;
                case 2: Operaciones.listar(); break;
                case 3: Operaciones.cancelar(sc);; break;
                case 4: Operaciones.reporte();; break;
                case 5: exit = true; break;
                default: System.out.println(ERROR_INPUT_MSG);
            }
        } while(!exit);
        
        sc.close();
    }
}
