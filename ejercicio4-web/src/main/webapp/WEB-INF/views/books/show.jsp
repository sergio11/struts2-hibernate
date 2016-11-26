<%-- 
    Document   : show
    Created on : 23-nov-2016, 23:47:27
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:property value="book.title"/></h2>

<div class="row">
    <s:if test="book.excerpt != null">
        <iframe class="pull-left" width="500" height="500" src="<s:url action="pdf" namespace="books" includeContext="false" includeParams="get">
                    <s:param name="idPdf">
                        <s:property value="book.excerpt.id"/>
                    </s:param>
                </s:url>"></iframe>
        </s:if>

    <div class="panel panel-default pull-left" style="margin: .2rem .5rem">
        <!-- Default panel contents -->
        <div class="panel-heading"><s:text name="titles.book.information.basic" /></div>
        <ul class="list-group">
            <li class="list-group-item">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <s:property value="book.title"/>
            </li>
            <li class="list-group-item">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <s:property value="book.pages"/> páginas
            </li>
            <li class="list-group-item">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <s:property value="book.isbn"/>
            </li>
            <li class="list-group-item">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <s:property value="book.publicationDate"/>
            </li>
            <li class="list-group-item">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <s:property value="book.level"/>
            </li>
        </ul>
    </div>
            
    <div class="panel panel-default pull-left" style="margin: .2rem .5rem">
        <!-- Default panel contents -->
        <div class="panel-heading"><s:text name="titles.book.information.authors" /></div>
        <ul class="list-group">
            <s:iterator var="author" value="book.authors">
                <li class="list-group-item">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <s:property value="author.fullname"/>
                </li>
            </s:iterator>
        </ul>
    </div>

</div>


            
<s:if test="book.description != null">
    <div class="panel panel-default pull-left" style="margin: 1rem .5rem">
        <!-- Default panel contents -->
        <div class="panel-heading"><s:text name="titles.book.information.description" /></div>
        <div class="panel-body">
            <p><s:property value="book.description"/></p>
        </div>
    </div>
</s:if>


