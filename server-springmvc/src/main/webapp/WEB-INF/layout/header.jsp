<%-- 
    Document   : header
    Created on : Aug 24, 2023, 9:50:58 AM
    Author     : maixuanvinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/ReviewFood/">Review Food</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <c:forEach items="${categoryRes}" var="cate">
                    <c:url value="/" var="searchUrl">
                        <c:param name="cateResId" value="${cate.id}"></c:param>
                    </c:url>
                    <li class="nav-item">
                        <a class="nav-link" href="${searchUrl}">${cate.name}</a>
                    </li>    
                </c:forEach>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <li class="nav-item active">
                            <a class="nav-link" href="<c:url value="/" />">${pageContext.request.userPrincipal.name}</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link text-danger" href="<c:url value="/" />">Log out</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item active">
                            <a class="nav-link text-danger" href="<c:url value="/login" />">Đăng nhập</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link text-danger" href="<c:url value="/register" />">Đăng ký</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <c:url value="/" var="action" />
        <form class="d-flex" action="${action}">
            <input class="form-control me-2" type="text" name="kw" placeholder="Search">
            <button class="btn btn-primary" type="submit">Search</button>
        </form>
    </div>
</nav>
