import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// SERVIDOR
// Arranca el registro RMI y publica la calculadora
// para que los clientes puedan encontrarla y usarla.
public class Servidor {
    
    public static void main(String[] args) {
        try {
            // PASO 1: Crear el objeto con la logica de la calculadora.
            // Al instaciar CalculadoraImpl, UnicastRemoteObject
            // lo deja listo para recibir llamadas por red.
            CalculadoraImpl calculadora = new CalculadoraImpl();

            // Paso 2: Crear el registro RMI en el puerto estandar 1099.
            // El Registry es el directorio donde se publican los objetos remotos.
            Registry registry = LocateRegistry.createRegistry(1099);

            // Paso 3: Publicar la calculadora con un nombre clave.
            // El cliente usara este mismo nombre en lookup() para encontrarla.
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