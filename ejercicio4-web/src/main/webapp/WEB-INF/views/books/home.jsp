<%-- 
    Document   : home
    Created on : 29-nov-2016, 23:38:17
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="jumbotron">
    <h1><s:text name="title.home.exercise"/></h1>
    <p><s:text name="title.home.exercise.description"/></p>
    <p>Componentes empleados:</p>
    <ul>
        <li>Struts2 v2.5.2</li>
        <li>guice v4.1.0 (guice-struts2, guice-jndi)</li>
        <li>struts2-tiles-plugin v2.5.2</li>
        <li>struts2-bootstrap-plugin v2.5.0</li>
        <li>struts2-jquery-plugin 4.0.1-SNAPSHOT</li>
        <li>pdfbox v1.8.12</li>
        <li>hibernate-search-orm v5.5.4.Final</li>
        <li>hibernate-entitymanager v4.3.1.Final</li>
    </ul>
    <p><s:a action="all" namespace="book" cssClass="btn btn-primary btn-lg"><s:text name="nav.book.all" /></s:a></p>
</div>



