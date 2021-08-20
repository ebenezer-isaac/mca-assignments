<%
    session.removeAttribute("customer_id");
    String redirectURL = "index.jsp";
    response.sendRedirect(redirectURL);
%>