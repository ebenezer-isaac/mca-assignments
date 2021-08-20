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
            System.out.println();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter ID of author to delete : ");
            int id = sc.nextInt();
            PreparedStatement ps = con.prepareStatement("delete from authors where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Author Deleted Successfully");
        } catch (Exception e) {
            System.out.println("An Error Occurred while deleting the record");
            e.printStackTrace();
        }
    }
}
