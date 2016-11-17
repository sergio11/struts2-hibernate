<%-- 
    Document   : persist
    Created on : 17-nov-2016, 18:38:10
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<s:form action="persistProccess" enctype="multipart/form-data" theme="bootstrap" 
        cssClass="form-horizontal">
    <s:textfield label="%{getText('forms.book.title.label')}" name="book.title" 
                 tooltip="%{getText('forms.book.title.tooltip')}"/>
    <s:textfield type="number" label="%{getText('forms.book.pages.label')}" name="book.pages" 
                 tooltip="%{getText('forms.book.pages.tooltip')}"/>
    <s:textfield label="%{getText('forms.book.isbn.label')}" name="book.isbn" 
                 tooltip="%{getText('forms.book.isbn.tooltip')}"/>
    <sj:datepicker
        id="datepicker"
        parentTheme="bootstrap"
        label="%{getText('forms.book.publicationDate.label')}"
        tooltip="%{getText('forms.book.publicationDate.tooltip')}"
        cssClass="form-control"
        elementCssClass="col-sm-5"
        showOn="focus"
        inputAppendIcon="calendar"
        name="book.publicationDate"
        displayFormat="dd/mm/yy"
         />
    <s:radio
        tooltip="%{getText('forms.book.level.tooltip')}"
        label="%{getText('forms.book.level.label')}"
        labelposition="inline"
        list="levels"
        name="book.level"/>
    <s:select
        tooltip="%{getText('forms.book.authors.tooltip')}"
        label="%{getText('forms.book.authors.label')}"
        list="{'Red', 'Blue', 'Green'}"
        name="book.authors"
        emptyOption="true"
        headerKey="fullname"
        headerValue="id"/>
    <s:textarea
        label="%{getText('forms.book.description.label')}"
        name="book.description"
        tooltip="%{getText('forms.book.description.tooltip')}"/> 
    <s:submit cssClass="btn" label="%{getText('forms.book.save')}" />
</s:form>

