import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteCalc {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            CalculadoraInterface calc = (CalculadoraInterface) registry.lookup("Calculadora");

            Scanner scanner = new Scanner(System.in);
            int opcion = -1;

            System.out.println("==============================");
            System.out.println(" Calculadora RMI");
            System.out.println("==============================");

            while (opcion != 5) {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Suma");
                System.out.println("2. Resta");
                System.out.println("3. Multiplicacion");
                System.out.println("4. Divicion");
                System.out.println("5. Salir");
                System.out.print("Elige una opcion: ");

                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println(" [!] Opcion invalida. Ingresa un numero del 1 al 5");
                    scanner.nextLine();
                    continue;
                }

                if (opcion >= 1 && opcion <= 4) {
                    System.out.print(" Numero A: ");
                    double a = 0, b = 0;

                    if (scanner.hasNextDouble()) {
                        a = scanner.nextDouble();
                    } else {
                        System.out.println("[!] Numero invalido");
                        scanner.nextLine();
                        continue;
                    }

                    System.out.print(" Numero B: ");

                    if (scanner.hasNextDouble()) {
                        b = scanner.nextDouble();
                        scanner.nextLine();
                    } else {
                        System.out.println("[!] Numero invalido");
                        scanner.nextLine();
                        continue;
                    }

                    switch (opcion) {
                        case 1:
                            System.out.println(" Resultado: " + a + " + " + b + " = " + calc.sumar(a, b));
                            break;
                        case 2:
                            System.out.println(" Resultado: " + a + " - " + b + " = " + calc.restar(a, b));
                            break;
                        case 3:
                            System.out.println(" Resultado: " + a + " * " + b + " = " + calc.multiplicar(a, b));
                            break;
                        case 4:
                            try {
                                System.out.println(" Resultado: " + a + " / " + b + " = " + calc.dividir(a, b));
                            } catch (Exception e) {
                                System.out.println(" [!] " + e.getMessage());
                            }
                            break;
                    }
                } else if (opcion == 5) {
                    System.out.println(" Cerrando calculadora. Chao chao");
                } else {
                    System.out.println(" [!] Opcion no valida. Elige entre 1 y 5");
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}