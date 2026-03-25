import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraImpl extends UnicastRemoteObject implements CalculadoraInterface {
    public CalculadoraImpl() throws RemoteException {
        super();
    }

    @Override
    public double sumar(double a, double b) throws RemoteException {
        double resultado = a + b;
        System.out.println("[Servidor] " + a + " + " + b + " = " + resultado);
        return resultado;
    }

    @Override
    public double restar(double a, double b) throws RemoteException {
        double resultado = a - b;
        System.out.println("[Servidor] " + a + " - " + b + " = " + resultado);
        return resultado;
    }

    @Override
    public double multiplicar(double a, double b) throws RemoteException {
        double resultado = a * b;
        System.out.println("[Servidor] " + a + " * " + b + " = " + resultado);
        return resultado;
    }

    @Override
    public double dividir(double a, double b) throws RemoteException {
        double resultado = a / b;
        System.out.println("[Servidor] " + a + " / " + b + " = " + resultado);
        return resultado;
    }

}