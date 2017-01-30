<%--
  Created by IntelliJ IDEA.
  User: yale
  Date: 20/09/16
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Chester Login</title>
  </head>
  <body>
  <form action="login" method="post">
    Username <input type="text" name="uname"><br>
    Password <input type="password" name="pass"><br>
    <input type="submit" value="Login">
    <% if (!(session.getAttribute("login_fail_msg")==null))%>
    <p> ${sessionScope.login_fail_msg}</p>
  </form>
  $END$
  </body>
</html>
