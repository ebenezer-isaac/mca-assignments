<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    <title>Anna University Registration</title>
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">
    <link href="css/main.css" rel="stylesheet" media="all">
</head>
<body>
<div class="page-wrapper bg-gra-01 p-t-180 p-b-100 font-poppins">
    <div class="wrapper wrapper--w780">
        <div class="card card-3">
            <div class="card-heading"></div>
            <div class="card-body" style="color:white">
                <h2 class="title">Registration Info</h2>
                <jsp:useBean id="student" scope="session" class="com.ebenezerisaac.student.Student">
                    <jsp:setProperty name="student" property="*"/>
                </jsp:useBean>
                <p> Name :
                    <jsp:getProperty name="student" property="name"/>
                </p>
                <p> Birthday :
                    <jsp:getProperty name="student" property="birthday"/>
                </p>
                <p> Email :
                    <jsp:getProperty name="student" property="email"/>
                </p>
                <p> Course :
                    <jsp:getProperty name="student" property="course"/>
                </p>
                <p> Semester :
                    <jsp:getProperty name="student" property="semester"/>
                </p>
            </div>
        </div>
    </div>
</div>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/select2/select2.min.js"></script>
<script src="vendor/datepicker/moment.min.js"></script>
<script src="vendor/datepicker/daterangepicker.js"></script>
<script src="js/global.js"></script>
</body>
</html>