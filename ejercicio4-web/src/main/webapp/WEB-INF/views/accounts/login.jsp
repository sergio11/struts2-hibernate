<%-- 
    Document   : login
    Created on : 09-nov-2016, 21:52:52
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <s:if test="#{parameters.auth}">
            <s:text name="auth.failed" />
        </s:if>
        <form action="<%=request.getContextPath()%>/j_security_check" method="POST">
            <s:text name="forms.login.username.label" /> <input type="text" name="j_username" /> <br />
            <s:text name="forms.login.password.label" /> <input type="password" name="j_password" /> <br />
            <input type="submit" value="<s:text name='forms.login.submit' />" />
        </form>
    </body>
</html>
