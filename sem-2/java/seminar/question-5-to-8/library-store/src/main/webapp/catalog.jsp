<jsp:include page="header.jsp"/>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.Objects" %>
<%!
    public String generateProduct(String id, String name, String image, String price, String author) {
        Object[] params = new Object[]{id, name, image, price, author};
        return MessageFormat.format(
                "<div class='product'>" +
                        "    <div class='product-img'>" +
                        "        <img class='text-center fa-align-center glyphicon-align-center' src='./img/" + image + "' alt='" + name + "'>" +
                        "    </div>" +
                        "    <div class='product-body '>" +
                        "        <p class='product-category'>{4}</p>" +
                        "        <h3 class='product-name'><a href='product.jsp?id=" + id + "'>{1}</a></h3>" +
                        "        <h4 class='product-price'>{3}</h4>" +
                        "        <div class='product-rating'>" +
                        "            <i class='fa fa-star'></i>" +
                        "            <i class='fa fa-star'></i>" +
                        "            <i class='fa fa-star'></i>" +
                        "            <i class='fa fa-star'></i>" +
                        "            <i class='fa fa-star'></i>" +
                        "        </div>" +
                        "    </div>" +
                        "    <div class='add-to-cart'>" +
                        "        <button class='add-to-cart-btn' onclick='javascript:window.location.replace(\"addtocart.jsp?id=" + id + "\")'>" +
                        "            <i class='fa fa-shopping-cart'></i> add to cart" +
                        "        </button>" +
                        "    </div>" +
                        "</div>", params);
    }
%>
<style>
    img {
        border-radius: 10px;
        offset-start: 40px;
        left: 200px;
        margin-top: 30px;
        margin-left: 85px;
        text-align: center;
    !important;
        align-content: center;
    !important;
        align-items: center;
    !important;
        align-self: center;
    !important;
        text-align-all: center;
    !important;
    }
</style>
<div class="section">
    <div class="container center-block ">
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h3 class="title">Our Products</h3>
                </div>
            </div>
            <%
                int counter = -1;
                String search = request.getParameter("search") == null ? "%%" : "%" + request.getParameter("search") + "%";
                try {
                    final String url = "jdbc:mysql://localhost:3306/library";
                    final String username = "root";
                    final String password = "";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, username, password);
                    PreparedStatement ps = con.prepareStatement("select books.book_id, books.name, books.url, books.price, authors.first_name, authors.last_name from books inner join authors on books.author_id = authors.author_id where name like ?");
                    ps.setString(1, search);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        counter += 1;
                        if (counter % 4 == 0) {
            %>
            <div class="col-md-12" style="margin-bottom:60px">
                <div class="row">
                    <div class="products-tabs">
                        <div class="products-slick" data-nav="#slick-nav-1">
                            <%
                                }
                                String id = rs.getString(1);
                                String name = rs.getString(2);
                                String image = rs.getString(3);
                                String price = "Rs. " + rs.getString(4);
                                String author = rs.getString(5) + " " + rs.getString(6);
                                System.out.println(name);
                            %>
                            <div>
                                <%=generateProduct(id, name, image, price, author)%>
                            </div>
                            <%
                                if (counter % 4 == 3) {
                            %>
                        </div>
                    </div>
                </div>
            </div>
            <%
                        }
                    }
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (counter % 4 != 3) {%></div></div></div></div><%}%>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>