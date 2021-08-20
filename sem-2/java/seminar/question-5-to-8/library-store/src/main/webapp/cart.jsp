<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%
    String customer_id = (String) session.getAttribute("customer_id");
    if (customer_id != null) {
        float total = 0;

%>
<jsp:include page="header.jsp"/>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12 order-details">
                <div class="section-title text-center">
                    <h3 class="title">Your Order</h3>
                </div>
                <div class="order-summary">
                    <div class="order-col">
                        <div><strong>PRODUCT</strong></div>
                        <div><strong>TOTAL</strong></div>
                    </div>
                    <div class="order-products">
                        <%
                            try {
                                final String url = "jdbc:mysql://localhost:3306/library";
                                final String username = "root";
                                final String password = "";
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection con = DriverManager.getConnection(url, username, password);
                                PreparedStatement ps = con.prepareStatement("SELECT cart.quantity, books.name, books.price FROM `cart` inner join books on cart.book_id=books.book_id where customer_id = ?");
                                ps.setInt(1, Integer.parseInt(customer_id));
                                ResultSet rs = ps.executeQuery();
                                while (rs.next()) {
                                    total += (rs.getInt(1) * rs.getFloat(3));
                        %>
                        <div class="order-col">
                            <div><%=rs.getInt(1)%> x <%=rs.getString(2)%>
                            </div>
                            <div>Rs. <%=(rs.getInt(1) * rs.getFloat(3))%>
                            </div>
                        </div>
                        <%

                                }
                                con.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                    </div>
                    <div class="order-col">
                        <div>Shiping</div>
                        <div><strong>FREE</strong></div>
                    </div>
                    <div class="order-col">
                        <div><strong>TOTAL</strong></div>
                        <div><strong class="order-total">Rs. <%=total%>
                        </strong></div>
                    </div>
                </div>
                <div class="input-checkbox">
                    <input type="checkbox" id="terms">
                    <label for="terms">
                        <span></span>
                        I've read and accept the <a href="#">terms & conditions</a>
                    </label>
                </div>
                <a href="#" class="col-md-3 primary-btn order-submit">Place order</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<%
    } else {
        String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
    }
%>