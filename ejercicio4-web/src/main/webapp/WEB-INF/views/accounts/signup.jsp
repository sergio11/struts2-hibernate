<%-- 
    Document   : signup
    Created on : 13-nov-2016, 14:01:51
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>


<s:form action="create" enctype="multipart/form-data" theme="bootstrap" 
        cssClass="form-horizontal">
    <s:textfield label="%{getText('forms.signup.username.label')}" name="user.userName" 
                 tooltip="%{getText('forms.signup.username.tooltip')}"/>
    <s:textfield label="%{getText('forms.signup.password.label')}" type="password" name="user.password"
                 tooltip="%{getText('forms.signup.password.tooltip')}" />
    <s:textfield label="%{getText('forms.signup.name.label')}" name="user.name" 
                 tooltip="%{getText('forms.signup.name.tooltip')}" />
    <s:textfield label="%{getText('forms.signup.lastname.label')}" name="user.lastname" 
                 tooltip="%{getText('forms.signup.lastname.tooltip')}" />
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
        displayFormat="dd/mm/yy"
         />
    
    <s:file
        tooltip="%{getText('forms.signup.profile.tooltip')}"
        label="%{getText('forms.signup.profile.label')}"
        name="upload"/>
    <s:submit cssClass="btn"/>
</s:form>
