<%-- 
    Document   : remove
    Created on : 21-nov-2016, 23:10:03
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="removeBook_<s:property value="id" />" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><s:text name="remove.books.legend" /></h4>
      </div>
      <div class="modal-body">
          <s:text name="confirm.book.remove">
            <s:param><s:property value="title" /></s:param>
         </s:text>
      </div>
      <div class="modal-footer">
          <s:a action="remove"  namespace="books" includeContext="false"  cssClass="btn btn-xs btn-danger">
              <s:text name="actions.books.remove" />
              <s:param name="idBook"><s:property value="id" /></s:param>
          </s:a>
      </div>
    </div>
  </div>
</div>
