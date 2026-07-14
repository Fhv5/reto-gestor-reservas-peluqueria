import java.util.function.Predicate;

public class Utils {
    public static String servicioMapping(int codServicio) {
        String retVal = "";

        switch (codServicio) {
            case 1: retVal = "Corte de Cabello"; break;
            case 2: retVal = "Tinte"; break;
            case 3: retVal = "Manicure"; break;            
        }

        return retVal;
    }

    public static void printReservas(Predicate<Integer> condicion) {
        String leftAlignFormat = "| %-4d | %-24s | %-6s | %-18s|%n";
        System.out.format("+------+--------------------------+--------+-------------------+%n");
        System.out.format("| ID   | Nombre                   | Hora   | Servicio          |%n");
        System.out.format("+------+--------------------------+--------+-------------------+%n");

        for (int i = 0; i < Operaciones.nReservas; i++) {
            if (condicion.test(i)) {
                System.out.printf(leftAlignFormat, 
                    i + 1, 
                    Operaciones.nombres[i], 
                    Operaciones.horas[i] + ":00", 
                    servicioMapping(Operaciones.codsServicio[i]));
            }
        }
        System.out.format("+------+--------------------------+--------+-------------------+%n");
    }
}
