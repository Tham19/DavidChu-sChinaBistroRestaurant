<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>David Chu's China Bistro Register</title>
    <meta charset="utf-8">
    <link href="/resources/css/login.css" rel='stylesheet' type='text/css'/>
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

    <script type="text/javascript">
        var serverContext = "<%=request.getContextPath()%>";

        $(document).ready(function () {
            $('form').submit(function (event) {
                resetPass(event);
            });
        });

        function resetPass(event) {
            var email = $("#email").val();
            $.post("/account/resetPassword", {email: email}, function (data) {
                /*window.location.href = serverContext + "/login";*/
            })
                .done(function () {
                    window.location.href = serverContext + "/login?message=send email success";
                })
                .fail(function (data) {
                    window.location.href = serverContext + "/login?message=send email failure";
                });
        }
    </script>
</head>
<body>
<!-----start-main---->
<div class="main">
    <div id="sendEmailForResetPasswordUserForm" class="login-form">
        <h1>David Chu's China Bistro</h1>
        <div class="head">
            <img src="/resources/images/user.png" alt="user"/>
        </div>
        <form:form action="/account/resetPassword" method="post">
            <input id="email" type="text" name="email" placeholder="email"/>

            <div class="submit">
                <input type="submit" class="btn btn-info btn-xs" value="SEND">
            </div>
        </form:form>
    </div>
</div>
<!-----//end-main---->

</body>
</html>


