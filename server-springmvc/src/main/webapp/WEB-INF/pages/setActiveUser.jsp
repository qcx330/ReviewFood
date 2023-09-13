<%-- 
    Document   : restaurant
    Created on : Aug 24, 2023, 10:05:33 AM
    Author     : maixuanvinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h1 class="text-center text-info">Quản lý nhà hàng</h1>

<c:url value="/active/user/test" var="action"/>
<form:form method="post" action="${action}" modelAttribute="user">
    <form:hidden path="id"/>   
    <div class="form-floating">
        <form:select class="form-select" id="active" name="active" path="active">
            <option value="1">Cho phép</option>
            <option value="0">Không cho phép</option>
        </form:select>
        <label for="sel1" class="form-label">Hoạt động:</label>
    </div>
    <div class="form-floating mt-1">
        <button class="btn btn-info" type="submit">
            <c:choose>
                <c:when test="${user.id == null}">Xác nhận</c:when>
                <c:otherwise>Cập nhật</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>