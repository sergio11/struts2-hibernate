<%-- 
    Document   : signup
    Created on : 13-nov-2016, 14:01:51
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
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

        <sj:head jqueryui="true"/>
        <sb:head includeScripts="true" includeScriptsValidation="false"/>
    </head>
    <body>
        <s:actionerror theme="bootstrap"/>
        <s:actionmessage theme="bootstrap"/>
        <s:fielderror theme="bootstrap"/>

        <s:form action="create" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal"
                    label="A sample horizontal Form">
            
            <s:textfield label="%{getText('forms.signup.username.label')}" name="user.userName" tooltip="%{getText('forms.signup.username.tooltip')}"/>
            <s:textfield label="%{getText('forms.signup.password.label')}" name="user.password" tooltip="%{getText('forms.signup.password.tooltip')}" />
            <s:textfield label="%{getText('forms.signup.name.label')}" name="user.name" tooltip="%{getText('forms.signup.name.tooltip')}" />
            <s:textfield label="%{getText('forms.signup.lastname.label')}" name="user.lastname" tooltip="%{getText('forms.signup.lastname.tooltip')}" />
            <s:checkboxlist
                tooltip="%{getText('forms.signup.groups.tooltip')}"
                labelposition="inline"
                label="%{getText('forms.signup.groups.label')}"
                list="allGroups"
                listKey="name"
                listValue="name"
                name="user.groups"/>
            <sj:datepicker
                id="datepicker"
                parentTheme="bootstrap"
                label="%{getText('forms.signup.birthday.label')}"
                tooltip="%{getText('forms.signup.birthday.tooltip')}"
                cssClass="form-control"
                elementCssClass="col-sm-5"
                showOn="focus"
                inputAppendIcon="calendar"
                name="user.birthday"
                />
            <s:submit cssClass="btn"/>
        </s:form>
    </body>
</html>
