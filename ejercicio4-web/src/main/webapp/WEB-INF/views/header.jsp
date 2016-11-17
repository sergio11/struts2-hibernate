<%-- 
    Document   : header
    Created on : 17-sep-2016, 18:40:13
    Author     : sergio
--%>

<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<nav class="navbar">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <s:a action="home" cssClass="navbar-brand">Books</s:a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
            <ul class="nav navbar-nav">
                <li><s:a action="employees"><s:text name="nav.link.list" /></s:a></li>
                <li><s:a action="show_data"><s:text name="nav.link.add" /></s:a></li>
            </ul>
        </div>
    </div>
</nav>
