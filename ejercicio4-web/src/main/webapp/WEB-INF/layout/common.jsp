<%-- 
    Document   : baseLayout
    Created on : 17-sep-2016, 12:02:42
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <title><tiles:getAsString name="title" ignore="true"/></title>
        <s:url var="base" value="/" />
        <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
        <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <sj:head jqueryui="true"/>
        <sb:head includeScripts="true" includeScriptsValidation="false"/>
        <link rel="stylesheet" href="css/slate.min.css" />
    </head>
    <body>
        <tiles:insertAttribute name="body" />
    </body>
</html>
