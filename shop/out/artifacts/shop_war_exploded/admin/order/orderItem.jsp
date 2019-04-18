<%--
  Created by IntelliJ IDEA.
  User: Benjamin
  Date: 2019/4/17
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<table border="0" width="100%">
    <s:iterator var="orderItem" value="list">
        <tr>
            <td><img width="40" height="45" src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>" alt=""></td>
            <td><s:property value="#orderItem.count"></s:property></td>
            <td><s:property value="#orderItem.subtotal"></s:property></td>
        </tr>
    </s:iterator>

</table>
