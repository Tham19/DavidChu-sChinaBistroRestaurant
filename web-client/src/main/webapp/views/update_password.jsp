<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>David Chu's China Bistro Update Password</title>
    <meta charset="utf-8">
    <link href="../resources/css/login.css" rel='stylesheet' type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--webfonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:600italic,400,300,600,700' rel='stylesheet'
          type='text/css'>
    <!--//webfonts-->

    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>

    <script>

        var serverContext = <%=request.getContextPath()%>;

        $(document).ready(function () {
            $('form').submit(function (event) {
                savePass(event);
            });

            $(":password").keyup(function () {
                if ($("#password").val() != $("#matchPassword").val()) {
                    $("#globalError").show().html("pass not match");
                } else {
                    $("#globalError").html("").hide();
                }
            });
        });

        function savePass(event) {
            event.preventDefault();
            $(".alert").html("").hide();
            $(".error-list").html("");
            if ($("#password").val() != $("#matchPassword").val()) {
                $("#globalError").show().html("pass not match");
                return;
            }
            var newPassword = $("#password").val();
            $.post("", {newPassword : newPassword}, function (data) {
                window.location.href = serverContext + "login?message=Change password success";
            })
                .done(function () {
                    window.location.href = serverContext + "login?message=Change password success";
                })
        }
    </script>

</head>
<body>
<!-----start-main---->
<div class="main">
    <div id="updatePasswordUserForm" class="login-form">
        <h1>David Chu's China Bistro</h1>
        <div class="head">
            <img src="../resources/images/user.png" alt="user"/>
        </div>
        <form:form action="/account/savePassword"  autocomplete="off" method="POST">
            <input id="password" type="text" name="newPassword" placeholder="password"/>

            <input id="matchPassword" type="password" name="confirm newPassword" placeholder="confirm password"/>
            <div id="globalError" class="col-sm-12 alert alert-danger" style="display:none">error</div>

            <div class="submit">
                <input type="submit" class="btn btn-info btn-xs" onclick="savePass()" value="UPDATE">
            </div>
            <div style="text-align: center;">
                <a href="/login"><span class="glyphicon glyphicon-chevron-left"></span> Back to login</a>
            </div>

        </form:form>
    </div>
</div>
<!-----//end-main---->

</body>
</html>


