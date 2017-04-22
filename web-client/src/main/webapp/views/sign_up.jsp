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
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
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

</head>
<body>
<!-----start-main---->
<div class="main">
    <div id="registerUserForm" class="login-form">
        <h1>David Chu's China Bistro</h1>
        <div class="head">
            <img src="/resources/images/user.png" alt="user"/>
        </div>
        <form:form action="saveAccount" commandName="accountDto" autocomplete="off" method="POST">
            <form:input path="username" type="text" name="username" placeholder="username"/>
            <form:errors path="username" cssClass="error"/>

            <form:input path="password" type="password" name="password" placeholder="password"/>
            <form:errors path="password" cssClass="error"/>

            <form:input path="confirmPassword" type="text" name="confirmPassword" placeholder="confirmPassword"/>
            <form:errors path="confirmPassword" cssClass="error"/>

            <form:input path="email" type="text" name="email" placeholder="email"/>
            <form:errors path="email" cssClass="error"/>

            <form:input path="phoneNumber" type="text" name="phoneNumber" placeholder="phoneNumber"/>
            <form:errors path="phoneNumber" cssClass="error"/>

            <div class="submit">
                <input type="submit" class="btn btn-info btn-xs" value="REGISTER">
            </div>
            <div style="text-align: center;">
                <a href="login"><span class="glyphicon glyphicon-chevron-left"></span> Back to login</a>
            </div>

        </form:form>
    </div>
</div>
<!-----//end-main---->

</body>
</html>


