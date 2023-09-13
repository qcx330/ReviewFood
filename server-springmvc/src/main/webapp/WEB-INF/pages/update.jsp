<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-danger">Đăng ký</h1>

<c:url value="/register" var="action"/>
<form:form method="post" action="${action}" modelAttribute="user">
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" for="firstName"
                    id="firstName" placeholder="Enter FirstName..." path="firstName"/>
        <label for="username" class="form-label">First Name</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" for="lastName"
                    id="lastName" placeholder="Enter LastName..." path="lastName"/>
        <label for="username" class="form-label">Last Name</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" for="userName"
                    id="useName" placeholder="Enter username..." path="userName"/>
        <label for="username" class="form-label">Username</label>
    </div>
        <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" for="email"
                    id="email" placeholder="Enter email..." path="email"/>
        <label for="username" class="form-label">Email</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="city" name="city" path="cityId">
            <c:forEach items="${city}" var="city">
                <option value="${city.id}">${city.name}</option>
            </c:forEach>
        </form:select>
        <label for="sel1" class="form-label">Thành Phố: </label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" for="phone"
                    id="phone" placeholder="Enter phone..." path="phone"/>
        <label for="phone" class="form-label">Phone</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" for="avatar"
                    id="avatar" placeholder="Enter avatar..." path="avatar"/>
        <label for="avatar" class="form-label">Avatar</label>
    </div>
        
     <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="userRole" name="userRole" path="userRole">
                <option value="ROLE_USER">USER</option>
                <option value="ROLE_RESTAURANT">RESTAURANT</option>
        </form:select>
        <label for="sel1" class="form-label">Đăng kí là: </label>
    </div>    
        
    <div class="form-floating mb-3 mt-3">
        <form:input type="password" for="password"
                    class="form-control" id="password" placeholder="Enter password..." path="password"/>
        <label for="password" class="form-label">Password</label>
    </div>
        <div class="form-floating mb-3 mt-3">
        <form:input type="password" for="confirmPass"
                    class="form-control" id="confirmPass" placeholder="Enter confirmPass..." path="confirmPass"/>
        <label for="password" class="form-label">Confirm Password</label>
    </div>
    <div class="form-floating mt-3 mb-3">
        <input type="submit" value="register" class="btn btn-danger" />
    </div>
</form:form>