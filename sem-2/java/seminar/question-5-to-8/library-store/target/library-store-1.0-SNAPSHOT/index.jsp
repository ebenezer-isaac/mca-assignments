<%
    String customer_id = (String) session.getAttribute("customer_id");
    if (customer_id == null) {%>
<jsp:include page="header.jsp"/>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-7">
                        <form class="billing-details">
                            <div class="section-title">
                                <h3 class="title">Create New Account</h3>
                            </div>
                            <div class="form-group">
                                <input class="input" type="text" name="name" placeholder="First Name">
                            </div>
                            <div class="form-group">
                                <input class="input" type="email" name="email" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <input class="input" type="password" name="password" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <input class="input" type="text" name="address" placeholder="City">
                            </div>
                            <div class="input-checkbox">
                                <input type="checkbox" id="terms">
                                <label for="terms">
                                    <span></span>
                                    I've read and accept the <a href="#">terms & conditions</a>
                                </label>
                            </div>
                            <input type="submit" class="primary-btn order-submit" value="Sign Up">
                        </form>
                    </div>

                    <!-- Order Details -->
                    <form class="col-md-5 order-details">
                        <div class="section-title text-center">
                            <h3 class="title">Login</h3>
                        </div>
                        <div class="order-summary">
                            <div class="form-group">
                                <input class="input" type="email" name="email" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <input class="input" type="password" name="password" placeholder="Password">
                            </div>
                        </div>
                        <input type="submit" class="primary-btn order-submit" value="Sign In">
                    </form>
                    <!-- /Order Details -->
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    let signupForm = document.querySelector("form.billing-details");
    signupForm.addEventListener("submit", e => {
        e.preventDefault()
        fetch("newcustomer.jsp?name=" + signupForm.name.value + "&email=" + signupForm.email.value + "&password=" + signupForm.password.value + "&address=" + signupForm.address.value)
            .then(response => {
                return response.text();
            }).then(data => {
            alert(data.trim());
            signupForm.reset();
            window.location.replace("catalog.jsp")
        }).catch(err => {
            alert(err)
            signupForm.reset();
        })
    })
    let loginForm = document.querySelector("form.order-details");
    loginForm.addEventListener("submit", e => {
        e.preventDefault()
        fetch("login.jsp?email=" + loginForm.email.value + "&password=" + loginForm.password.value)
            .then(response => {
                return response.text();
            }).then(data => {
            alert(data.trim());
            loginForm.reset();
            window.location.replace("catalog.jsp")
        }).catch(err => {
            alert(err)
            loginForm.reset();
        })
    })
</script>
<jsp:include page="footer.jsp"/>
<%
    } else {
        String redirectURL = "catalog.jsp";
        response.sendRedirect(redirectURL);
    }
%>