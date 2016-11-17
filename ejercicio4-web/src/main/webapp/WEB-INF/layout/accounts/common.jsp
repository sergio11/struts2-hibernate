<%-- 
    Document   : appLayout
    Created on : 17-nov-2016, 18:47:47
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<div class="container">
    <tiles:insertAttribute name="alerts" />
    <tiles:insertAttribute name="content" />
</div>