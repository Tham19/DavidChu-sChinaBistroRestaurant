<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page session="true" %>
<html>
<head>
    <meta charset="utf-8">
    <link href="../resources/css/login.css" rel='stylesheet'
          type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--webfonts-->
    <link
            href='http://fonts.googleapis.com/css?family=Open+Sans:600italic,400,300,600,700'
            rel='stylesheet' type='text/css'>
    <!--//webfonts-->
</head>
<body>
<div class="main">
    <div class="login-form">
        <h1>David Chu's China Bistro</h1>
        <br/>

        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
            <p style="align-content: center" class="alert-danger">
                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
            </p>
        </c:if>

        <c:if test="${not empty loginMessage}">
            <div class="msg alert-success" style="text-align: center">${loginMessage}</div>
        </c:if>

        <br/>

        <div class="head">
            <img src="../resources/images/user.png" alt="user"/>
        </div>


        <form action="/j_spring_security_check"
              method="post">
            <input type="text" class="text" name="username">
            <input type="password" name="password">
            <div class="submit">
                <input type="submit" value="LOGIN">
            </div>

            <label>Remember Me</label>
            <input name="remember-me-param" type="checkbox" checked="checked"/>
            <a href="/forgotPassword" style="text-align: right">Forgot password ?</a>
            <div style="text-align: center">
                <a id="my_register" class="text_right" href="sign_up">Create
                    new account<span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </form>

        <%--<input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />--%>
    </div>
    <!--//End-login-form-->
</div>
</body>
</html>


