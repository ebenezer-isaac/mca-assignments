import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

interface RMIAppInterface extends Remote {
    void printMsg() throws RemoteException;
}

class RMIAppFunctions implements RMIAppInterface {
    public void printMsg() {
        System.out.println("This is an example RMI program");
    }
}

public class server extends RMIAppFunctions {
    public server() {
    }

    public static void main(String[] args) {
        try {
            RMIAppFunctions obj = new RMIAppFunctions();
            RMIAppInterface stub = (RMIAppInterface) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("RMIAppInterface", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 