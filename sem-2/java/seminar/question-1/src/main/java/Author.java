import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Author {
    public static void insert(Connection con) {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Name :");
        String firstname = sc.nextLine();
        System.out.println("Enter Last Name :");
        String lastname = sc.nextLine();
        try {
            PreparedStatement ps = con.prepareStatement("insert into authors(firstname, lastname) values(?,?)");
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.executeUpdate();
            System.out.println("Record Inserted Successfully");
        } catch (Exception e) {
            System.out.println("An Error Occurred while inserting record");
            e.printStackTrace();
        }
    }

    public static void display(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from authors");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(
                        rs.getString(1) + "," +
                                rs.getString(2) + "," +
                                rs.getString(3) + ","
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("truncate authors");
            ps.executeUpdate();
            System.out.println("Records Emptied Successfully");
        } catch (Exception e) {
            System.out.println("An Error Occurred while deleting records");
            e.printStackTrace();
        }
    }
}
