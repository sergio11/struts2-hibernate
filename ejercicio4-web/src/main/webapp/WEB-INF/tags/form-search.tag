<%-- 
    Document   : form-search
    Created on : 22-nov-2016, 22:45:05
    Author     : sergio
--%>

<%@tag description="Search Form" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form method="GET" cssClass="form-inline pull-right">
    <div class="form-group">
        <div class="input-group">
            <div class="input-group-addon"><span class="glyphicon glyphicon-search"></span></div>
            <input type="text" name="query" class="form-control" value="<s:property value="query" />" />
        </div>
    </div>
    <button type="submit" class="btn btn-primary">
        <s:text name="forms.search.submit" />
    </button>
</s:form>