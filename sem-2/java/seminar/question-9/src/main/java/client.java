import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;  

public class client {
   private client() {}
   public static void main(String[] args) {  
      try {
         Registry registry = LocateRegistry.getRegistry(null);
         RMIAppInterface stub = (RMIAppInterface) registry.lookup("RMIAppInterface");
         stub.printMsg();
      } catch (Exception e) {
         System.err.println("Client exception: " + e.getMessage());
         e.printStackTrace(); 
      } 
   } 
}