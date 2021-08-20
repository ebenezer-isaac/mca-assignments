<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%
    if (request.getParameter("name") != null
            && request.getParameter("email") != null
            && request.getParameter("password") != null
            && request.getParameter("address") != null) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        String address = request.getParameter("address");
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            BigInteger number = new BigInteger(1, md.digest(pwd.getBytes(StandardCharsets.UTF_8)));
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }
            pwd = hexString.toString();
            final String url = "jdbc:mysql://localhost:3306/library";
            final String username = "root";
            final String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement(
                    "insert into customers(name, email, password, address) values(?,?,?,?)"
            );
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pwd);
            ps.setString(4, address);
            int result = ps.executeUpdate();
            if (result == 1) {
                ps = con.prepareStatement(
                        "select customer_id from customers where email = ?"
                );
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    session.setAttribute("customer_id", rs.getString(1));
%><%="Account Created Successfully"%><%
    }
} else {
%><%="Error Occured while creating new Account"%><%
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>