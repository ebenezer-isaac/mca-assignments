<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Library Online Store</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="css/slick.css"/>
    <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>
    <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<header>
    <div id="header">
        <div class="container">
            <div class="row">
                <div class="col-md-7">
                    <div class="header-search">
                        <form id="search">
                            <input class="input" name="search" placeholder="Search here">
                            <button class="search-btn">Search</button>
                        </form>
                        <script>
                            let form = document.querySelector("#search");
                            form.addEventListener('submit', e => {
                                e.preventDefault()
                                window.location.replace("catalog.jsp?search=" + form.search.value)
                            })
                        </script>
                    </div>
                </div>
                <div class="col-md-5 clearfix">
                    <div class="header-ctn">
                        <div>
                            <a href="catalog.jsp">
                                <i class="fa fa-book"></i>
                                <span>Catalog</span>
                            </a>
                        </div>
                        <%
                            String customer_id = (String) session.getAttribute("customer_id");
                            if (customer_id != null) {
                                int cart_size = 0;
                                try {
                                    final String url = "jdbc:mysql://localhost:3306/library";
                                    final String username = "root";
                                    final String password = "";
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection con = DriverManager.getConnection(url, username, password);
                                    PreparedStatement ps = con.prepareStatement("select count(cart_item_id) from cart where customer_id = ?");
                                    ps.setString(1, customer_id);
                                    ResultSet rs = ps.executeQuery();
                                    if (rs.next()) {
                                        cart_size = rs.getInt(1);

                                    }
                                    con.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                        %>
                        <div>
                            <a href="signout.jsp">
                                <i class="fa fa-sign-out"></i>
                                <span>Sign Out</span>
                            </a>
                        </div>
                        <div>
                            <a href="cart.jsp">
                                <i class="fa fa-shopping-cart"></i>
                                <span>Your Cart</span>
                                <div class="qty"><%=cart_size%>
                                </div>
                            </a>
                        </div>
                        <%
                        } else {
                        %>
                        <div>
                            <a href="index.jsp">
                                <i class="fa fa-sign-in"></i>
                                <span>Sign In</span>
                            </a>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>