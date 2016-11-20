<%-- 
    Document   : create
    Created on : 19-nov-2016, 15:13:24
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="addAuthor" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><s:text name="forms.author.legend" /></h4>
      </div>
      <div class="modal-body">
        <s:url var="createAuthor" action="create" namespace="/authors" includeContext="false"/> 
        <s:form id="addAuthorForm" action="%{createAuthor}" theme="bootstrap">
            <s:textfield label="%{getText('forms.author.name.label')}" name="author.name" 
                 tooltip="%{getText('forms.author.name.tooltip')}"/>
            <s:textfield label="%{getText('forms.author.lastname.label')}" name="author.lastname" 
                 tooltip="%{getText('forms.author.lastname.tooltip')}"/>
        </s:form>
      </div>
      <div class="modal-footer">
        <s:submit form="addAuthorForm" cssClass="btn btn-success" value="%{getText('actions.add')}" />
      </div>
    </div>
  </div>
</div>
