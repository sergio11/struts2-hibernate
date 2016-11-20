<%-- 
    Document   : books
    Created on : 09-nov-2016, 23:15:02
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="titles.book.all" /></h2>
<h3>Se han encontrado <s:property value="books.size" /> libros</h3>
<div class="row">
    <s:a action="persist" cssClass="btn btn-primary"><s:text name="actions.add" /></s:a>
</div>
<table class="table">
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
                    <td class="text-uppercase"><s:property value="authors.size" /></td>
                    <td class="text-uppercase">
                        <div class="btn-group">
                            <s:url var="persistURL" action="persist" namespace="books" includeContext="false">
                                <s:param name="idBook"><s:property value="id" /></s:param>
                            </s:url>
                            <s:a href="%{#persistURL}" cssClass="btn btn-xs btn-primary"><s:text name="actions.books.edit" /></s:a>
                            <button type="button" class="btn btn-xs btn-info"><s:text name="actions.books.excerpt" /></button>
                            <button type="button" class="btn btn-xs btn-danger"><s:text name="actions.books.remove" /></button>
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

