<%-- 
    Document   : show
    Created on : 23-nov-2016, 23:47:27
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:property value="book.title"/></h2>

<s:if test="book.excerpt != null">
    <iframe width="500" height="500" src="<s:url action="pdf" namespace="books" includeContext="false" includeParams="get">
        <s:param name="idPdf">
            <s:property value="book.excerpt.id"/>
        </s:param>
    </s:url>"></iframe>
</s:if>

