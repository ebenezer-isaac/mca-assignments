<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%
    if (request.getParameter("email") != null && request.getParameter("password") != null) {
        String loginUsername = request.getParameter("email");
        String loginPassword = request.getParameter("password");
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            BigInteger number = new BigInteger(1, md.digest(loginPassword.getBytes(StandardCharsets.UTF_8)));
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }
            loginPassword = hexString.toString();
            final String url = "jdbc:mysql://localhost:3306/library";
            final String username = "root";
            final String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("select customer_id from customers where email = ? and password = ?");
            ps.setString(1, loginUsername);
            ps.setString(2, loginPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
%><%="Login Successful"%><%
    String customer_id = rs.getString(1);
    session.setAttribute("customer_id", customer_id);
} else {
%><%="Invalid Username or Password"%><%
        }
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
} else {
%><%="outside Invalid Username or Password"%><%
    }
%>
