<%-- 
    Document   : alerts
    Created on : 17-sep-2016, 18:39:35
    Author     : sergio
--%>

<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:actionerror theme="bootstrap" title="%{alerts.action.errors.title}"/>
<s:actionmessage theme="bootstrap" title="%{alerts.action.message.title}"/>
<s:fielderror theme="bootstrap" title="%{alerts.field.errors.title}"/>
