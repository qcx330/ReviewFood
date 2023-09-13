<%-- 
    Document   : index
    Created on : Aug 23, 2023, 9:48:19 AM
    Author     : maixuanvinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container mt-3">
    <a href="<c:url value="/restaurant" />" class="btn btn-info">Thêm sản phẩm</a>
    <table class="table">
        <thead>
            <tr>
                <th>Image</th>
                <th>Id</th>
                <th>Tên nhà hàng</th>
                <th>Địa chỉ</th>
            </tr>
        <script src="<c:url value="/js/main.js"/>"></script>
        </thead>
        <tbody>
            <c:forEach items="${restaurant}" var="res">
                <tr>
                    <td>
                        <img src="${res.image}" alt="${res.name}" width="120" />
                    </td>
                    <td>${res.id}</td>
                    <td>${res.name}</td>
                    <td>${res.address}</td>
                    <td>
                        <c:url value="/api/restaurant/${res.id}" var="apiDel" />
                        <a href="<c:url value="/restaurant/${res.id}" />" class="btn btn-success">Duyệt</a>
                        <button class="btn btn-danger" onclick="delRes('${apiDel}')">Hủy</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${countRestaurant > 1}">
        <ul class="pagination pagination-sm">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end="${countRestaurant}" var="count">
                    <c:url value="/" var="pageUrl">
                        <c:param name="page" value="${count}"></c:param>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${count}</a></li>
                </c:forEach>
        </ul>
    </c:if>
</div>

