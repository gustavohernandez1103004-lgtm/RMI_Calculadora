import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

// CLIENTE
// Se conecta al servidor, obtiene la calculadora remota
// y permite al ususario operar con un menu interactivo.
public class ClienteCalc {

    public static void main(String[] args) {
        try {
            // PASO 1: Conectar al Registry del servidor.
            // "localhost" = misma maquina. Cambiar por IP real si esta en otra maquina.
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // PASO 2: Obtener el objeto remoto por su nombre clave.
            // lookup() retorna un Stub (proxy) que intercepta cada llamada
            // y la envia por red al servidor real.
            CalculadoraInterface calc = (CalculadoraInterface) registry.lookup("Calculadora");

            Scanner scanner = new Scanner(System.in);
            int opcion = -1;

            System.out.println("==============================");
            System.out.println(" Calculadora RMI");
            System.out.println("==============================");

            // Bucle principal: el menu se repite hasta que el usuario elija Salir
            while (opcion != 5) {
                System.out.println("\n--- MENU ---");
                System.out.println("1. Suma");
                System.out.println("2. Resta");
                System.out.println("3. Multiplicacion");
                System.out.println("4. Divicion");
                System.out.println("5. Salir");
                System.out.print("Elige una opcion: ");

                // Validar que el input sea un numero entero
                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer
                } else {
                    System.out.println(" [!] Opcion invalida. Ingresa un numero del 1 al 5");
                    scanner.nextLine();
                    continue;
                }

                // Opciones 1 a 4 necesitan dos numeros: se leen aqui
                if (opcion >= 1 && opcion <= 4) {
                    System.out.print(" Numero A: ");
                    double a = 0, b = 0;

                    // Leer y validar el primer numero
                    if (scanner.hasNextDouble()) {
                        a = scanner.nextDouble();
                    } else {
                        System.out.println("[!] Numero invalido");
                        scanner.nextLine();
                        continue;
                    }

                    System.out.print(" Numero B: ");

                    // Leer y validar el segundo nuemro
                    if (scanner.hasNextDouble()) {
                        b = scanner.nextDouble();
                        scanner.nextLine(); // limpiar buffer
                    } else {
                        System.out.println("[!] Numero invalido");
                        scanner.nextLine();
                        continue;
                    }

                    // Ejecutar la operacion elegida
                    // Cada llamada viaja por red al servidor, que calcula y devuelve el resultado.
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
                            // Divicion: captura la excepcion si el usuario intenta dividir entre cero
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