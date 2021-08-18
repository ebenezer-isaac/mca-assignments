import java.sql.Connection;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        System.out.println("hello");
        Connection con = DBHelper.getConnection();
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(
                    """
                            
                            Choose from the following :
                            1. Display records
                            2. Insert records
                            3. Delete all records
                            4. Exit
                                                        
                            """
            );
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Author.display(con);
                    break;
                case 2:
                    Author.insert(con);
                    break;
                case 3:
                    Author.delete(con);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        } while (choice != 4);
    }
}
