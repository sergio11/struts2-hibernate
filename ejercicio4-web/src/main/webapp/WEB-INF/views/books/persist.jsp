<%-- 
    Document   : persist
    Created on : 17-nov-2016, 18:38:10
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<h2><s:text name="titles.book.persist" /></h2>

<s:include  value="/WEB-INF/views/authors/create.jsp" />
<s:url var="persistProccess" action="persistProccess" namespace="/books" includeContext="false"/>

<s:if test="%{idBook != null and bookEdit == null}">
    <div class="alert alert-danger" role="alert"><s:text name="errors.books.notfound" /></div>
</s:if>

<s:form action="%{persistProccess}" enctype="multipart/form-data" theme="bootstrap" 
        cssClass="form-horizontal">
    <s:textfield label="%{getText('forms.book.title.label')}" name="book.title" 
                 tooltip="%{getText('forms.book.title.tooltip')}" value="%{bookEdit.title}"/>
    <s:textfield type="number" label="%{getText('forms.book.pages.label')}" name="book.pages" 
                 tooltip="%{getText('forms.book.pages.tooltip')}" value="%{bookEdit.pages}"/>
    <s:textfield label="%{getText('forms.book.isbn.label')}" name="book.isbn" 
                 tooltip="%{getText('forms.book.isbn.tooltip')}" value="%{bookEdit.isbn}"/>
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
        value="%{bookEdit.publicationDate}"
         />
    <s:radio
        tooltip="%{getText('forms.book.level.tooltip')}"
        label="%{getText('forms.book.level.label')}"
        labelposition="inline"
        list="levels"
        name="book.level"
        value="%{bookEdit.level}"/>
    <div class="row">
        <div class="col-md-11">
            <s:select
                tooltip="%{getText('forms.book.authors.tooltip')}"
                label="%{getText('forms.book.authors.label')}"
                disabled="%{authors.size == 0}"
                list="authors"
                name="book.authors"
                emptyOption="false"
                listKey="id"
                listValue="fullname"
                value="%{bookEdit.authors}"/>
        </div>
        <div class="col-md-1 text-left">    
            <button type="button" class="btn btn-primary pull-left" 
                data-toggle="modal" data-target="#addAuthor">
                 <s:text name="actions.add" />       
            </button>
        </div>
    </div>
    <s:textarea
        label="%{getText('forms.book.description.label')}"
        name="book.description"
        tooltip="%{getText('forms.book.description.tooltip')}"
        value="%{bookEdit.description}"/>
    <s:if test="bookEdit.id">
        <s:hidden name="book.id" value="%{bookEdit.id}" />
        <s:submit cssClass="btn btn-success pull-right" value="%{getText('forms.book.update')}" />
    </s:if>
    <s:else>
        <s:submit cssClass="btn btn-success pull-right" value="%{getText('forms.book.save')}" />
    </s:else>
  
</s:form>

