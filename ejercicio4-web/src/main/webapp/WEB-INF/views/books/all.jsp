<%-- 
    Document   : books
    Created on : 09-nov-2016, 23:15:02
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1>Hello <s:property value="principal.remoteUser.name" /></h1>
<h2>Número de libros: <s:property value="books.size" /></h2>

