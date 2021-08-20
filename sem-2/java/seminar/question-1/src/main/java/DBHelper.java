import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
    public static Connection getConnection(){

        final String url = "jdbc:mysql://localhost:3306/library";
        final String username = "root";
        final String password = "";
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }

}
