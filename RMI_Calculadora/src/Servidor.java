import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    
    public static void main(String[] args) {
        try {
            CalculadoraImpl calculadora = new CalculadoraImpl();

            Registry registry = LocateRegistry.createRegistry(1099);

            registry.rebind("Calculadora", calculadora);

            System.out.println("============================");
            System.out.println(" Servidor RMI iniciado");
            System.out.println(" Puerto : 1099");
            System.out.println(" Servicio: Calculadora");
            System.out.println(" Esperando Cliente...");
            System.out.println("============================");

        }  catch (Exception e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
