<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%
    String customer_id = (String) session.getAttribute("customer_id");
    if (customer_id != null) {
        if (request.getParameter("id") != null) {
            int book_id = Integer.parseInt(request.getParameter("id"));
            int quantity = request.getParameter("quantity") == null ? 1 : Integer.parseInt(request.getParameter("search"));
            try {
                final String url = "jdbc:mysql://localhost:3306/library";
                final String username = "root";
                final String password = "";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = con.prepareStatement(
                        "select cart_item_id, quantity from cart where book_id = ? and customer_id = ?"
                );
                ps.setInt(1, book_id);
                ps.setInt(2, Integer.parseInt(customer_id));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int cart_item_id = rs.getInt(1);
                    quantity += rs.getInt(2);
                    ps = con.prepareStatement(
                            "update cart set quantity=? where cart_item_id = ?"
                    );
                    ps.setInt(1, quantity);
                    ps.setInt(2, cart_item_id);
                    ps.executeUpdate();
                } else {
                    ps = con.prepareStatement(
                            "insert into cart(customer_id, book_id, quantity) value(?,?,?)"
                    );
                    ps.setInt(1, Integer.parseInt(customer_id));
                    ps.setInt(2, book_id);
                    ps.setInt(3, quantity);
                    ps.executeUpdate();
                }
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String redirectURL = "cart.jsp";
            response.sendRedirect(redirectURL);
        } else {
            String redirectURL = "catalog.jsp";
            response.sendRedirect(redirectURL);
        }
    } else {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
    }
%>