<%-- 
    Document   : signup
    Created on : 13-nov-2016, 14:01:51
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
        <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <sb:head/>
    </head>
    <body>
        <s:actionerror theme="bootstrap"/>
        <s:actionmessage theme="bootstrap"/>
        <s:fielderror theme="bootstrap"/>

        <s:form action="create" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal"
                    label="A sample horizontal Form">
            
            <s:textfield label="Username" name="userName" tooltip="Enter username here"/>
            <s:textfield label="Password" name="password" tooltip="Enter password here" />
            <s:textfield label="Name" name="name" tooltip="Enter name here" />
            <s:textfield label="Lastname" name="lastname" tooltip="Enter lastname here" />
            <s:checkboxlist
                tooltip="select roles for user"
                labelposition="inline"
                label="Roles"
                list="allGroups"
                name="groups"/>
            <s:submit cssClass="btn"/>
        </s:form>
    </body>
</html>
