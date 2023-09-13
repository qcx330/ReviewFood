<%-- 
    Document   : restaurant
    Created on : Aug 24, 2023, 10:05:33 AM
    Author     : maixuanvinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h1 class="text-center text-info">THÔNG TIN CHI TIẾT</h1>

<c:url value="/restaurant" var="action"/>
<form:form method="post" action="${action}" modelAttribute="restaurant" enctype="multipart/form-data">
    <form:hidden path="id"/>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="image" id="image " placeholder="Link ảnh..."/>
        <label for="email">Hình ảnh: </label>
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Nhập tên..."/>
        <label for="email">Tên nhà hàng: </label>
    </div>   
    <div class="form-floating">
        <form:select class="form-select" id="categoryRes" name="categoryRes" path="categoryResId">
            <c:forEach items="${categoryRes}" var="cate">
                <option value="${cate.id}">${cate.name}</option>
            </c:forEach>
        </form:select>
        <label for="sel1" class="form-label">Thuộc loại ẩm thực:</label>
    </div>
    <div class="form-floating mt-3 mb-3">
        <form:input type="text" class="form-control" path="address" id="address" placeholder="Nhập địa chỉ..."/>
        <label for="pwd">Địa chỉ: </label>
    </div>
    <div class="form-floating mt-3 mb-3">
        <form:input type="text" class="form-control" path="userId" id="userId" placeholder="Nhập userName..."/>
        <label for="pwd">Tài khoản: </label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" id="city" name="city" path="cityId">
            <c:forEach items="${city}" var="city">
                <<option value="${city.id}">${city.name}</option>
            </c:forEach>
        </form:select>
        <label for="sel1" class="form-label">Thành phố:</label>
    </div>
    <div class="form-floating mt-1">
        <button class="btn btn-info" type="submit">
            <c:choose>
                <c:when test="${restaurant.id == null}">Xác nhận</c:when>
                <c:otherwise>Cập nhật nhà hàng</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>