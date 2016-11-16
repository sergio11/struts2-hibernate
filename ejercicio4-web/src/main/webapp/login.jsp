<%-- 
    Document   : login
    Created on : 09-nov-2016, 21:52:52
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Log In</h1>
        <form action="j_security_check" method=post>
            <p>Usuario : <input type="text" name="j_username"/><br>
            <p>Contraseña : <input type="password" name = "j_password"/><br>
            <p><input type ="submit" name = "submit" value = "Ingresar">
        </form>
    </body>
</html>
