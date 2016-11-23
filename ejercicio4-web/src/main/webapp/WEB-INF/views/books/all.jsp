<%-- 
    Document   : books
    Created on : 09-nov-2016, 23:15:02
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/utils" %>


<h2><s:text name="titles.book.all" /></h2>
<s:if test="query == null">
    <h3>
        <s:text name="title.book.count">
            <s:param name="count">
                <s:property value="books.size" />
            </s:param>
        </s:text>
    </h3>
</s:if>
<s:else>
    <h3>
        <s:text name="title.book.filter.count">
            <s:param name="count">
                <s:property value="books.size" />
            </s:param>
            <s:param name="filter">
                <s:property value="query" />
            </s:param>
        </s:text>
    </h3>
</s:else>
<div class="row" style="margin:.5rem 0;">
    <s:a action="persist" cssClass="btn btn-success pull-right"><s:text name="actions.add" /></s:a>
    <util:form-search  />
</div>
<table class="table table-condensed table-striped">
    <thead>
        <th>#</th>
        <th><s:text name="table.books.thead.title" /></th>
        <th><s:text name="table.books.thead.pages" /></th>
        <th><s:text name="table.books.thead.isbn" /></th>
        <th><s:text name="table.books.thead.publicationDate" /></th>
        <th><s:text name="table.books.thead.level" /></th>
        <th><s:text name="table.books.thead.authors" /></th>
        <th><s:text name="table.books.thead.actions" /></th>
    </thead>
    <tbody>
        <s:if test="books.size">
            <s:iterator var="book" value="books">
                <tr>
                    <td class="text-uppercase"><s:property value="id" /></td>
                    <td class="text-uppercase"><s:property value="title" /></td>
                    <td class="text-uppercase"><s:property value="pages" /></td>
                    <td class="text-uppercase"><s:property value="isbn" /></td>
                    <td class="text-uppercase"><s:property value="publicationDate" /></td>
                    <td class="text-uppercase"><s:property value="level" /></td>
                    <td class="text-uppercase"><s:property value="%{authors.size()}" /></td>
                    <td class="text-uppercase">
                        <s:include value="/WEB-INF/views/books/remove.jsp">
                            <s:param name="book" value="book" />
                        </s:include>
                        <div class="btn-group">
                            <s:a action="persist" namespace="books" includeParams="get" cssClass="btn btn-xs btn-success">
                                <s:text name="actions.books.edit" />
                                <s:param name="idBook"><s:property value="id" /></s:param>
                            </s:a>
                            <button type="button" class="btn btn-xs btn-info"><s:text name="actions.books.excerpt" /></button>
                            <button type="button" class="btn btn-xs btn-danger" 
                                    data-toggle="modal" data-target="#removeBook_<s:property value="id" />"><s:text name="actions.books.remove" /></button>
                        </div>
                    </td>
                </tr>
            </s:iterator>
        </s:if>
        <s:else>
            <tr class="warning">
                <td colspan="8" class="text-center"><s:text name="warnings.books.notfound" /></td>
            </tr>
        </s:else>
    </tbody>
</table>