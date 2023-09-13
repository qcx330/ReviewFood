<%-- 
    Document   : index
    Created on : Aug 23, 2023, 9:48:19 AM
    Author     : maixuanvinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container mt-3">
    <table class="table">
        <thead>
            <tr>
                <th>Tên tài khoản</th>
                <th>Email</th>
                <th>Phone</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listUser}" var="user">
                <tr>
                    <td>
                        <img src="${p.image}" alt="${p.name}" width="120" />
                    </td>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>
                        <c:url value="/api/products/${p.id}" var="apiDel" />
                        <a href="<c:url value="/products/${p.id}" />" class="btn btn-success">Cập nhật</a>
                        <button class="btn btn-danger" onclick="delPro('${apiDel}', ${p.id})">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

