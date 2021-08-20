<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%
    if (request.getParameter("id") != null) {
        String book_id = request.getParameter("id");
        try {
            final String url = "jdbc:mysql://localhost:3306/library";
            final String username = "root";
            final String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement("select books.name, books.description, books.url, books.price, authors.first_name, authors.last_name from books inner join authors on books.author_id = authors.author_id where book_id = ?");
            ps.setInt(1, Integer.parseInt(book_id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString(1);
                String description = rs.getString(2);
                String image = rs.getString(3);
                String price = "Rs. " + rs.getString(4);
                String author = rs.getString(5) + " " + rs.getString(6);
%>
<jsp:include page="header.jsp"/>
<div class="section">
    <div class="container">
        <div class="row">
            <!-- Product main img -->
            <div class="col-md-5 col-md-push-2">
                <div id="product-main-img">
                    <div class="product-preview">
                        <img src="./img/<%=image%>" alt="<%=name%>">
                    </div>
                </div>
            </div>
            <!-- /Product main img -->

            <!-- Product thumb imgs -->
            <div class="col-md-2  col-md-pull-5">
                <div id="product-imgs">
                    <div class="product-preview">
                       <img src="./img/<%=image%>" alt="<%=name%>">
                    </div>
                </div>
            </div>
            <!-- /Product thumb imgs -->

            <!-- Product details -->
            <div class="col-md-5">
                <div class="product-details">
                    <h2 class="product-name"><%=name%></h2>
                    <div>
                        <h3 class="product-price"><%=price%></h3>
                        <span class="product-available">In Stock</span>
                    </div>
                    <p><%=description%></p>
                    <div class="add-to-cart">
                        <button onclick='javascript:window.location.replace("addtocart.jsp?id="+<%=book_id%>)' class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                    </div>
                    <ul class="product-links">
                        <li>Author : <%=author%></li>
                    </ul>
                </div>
            </div>
            <!-- Product details -->
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<%
            con.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<%
    } else {
        String redirectURL = "catalog.jsp";
        response.sendRedirect(redirectURL);
    }
%>