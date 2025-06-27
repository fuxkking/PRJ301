<%-- 
    Document   : header
    Created on : Jun 27, 2025, 4:05:06 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <header class="navbar">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/Views/Home.jsp">TRANG CHỦ</a>
            <a href="#">THỰC ĐƠN</a>
            <a href="#">GIỚI THIỆU</a>
            <a href="#">ĐẶT BÀN</a>
        </nav>
        <c:if test="${sessionScope.account == null}">
            <button class="log-resgister">
                <a href="${pageContext.request.contextPath}/Views/Login.jsp">Login </a>|
                <a href="${pageContext.request.contextPath}/Views/Register.jsp">Sign Up</a>
            </button>
        </c:if>
        <c:if test="${sessionScope.account != null}">
            <a href="${pageContext.request.contextPath}/Views/Profile.jsp" class="User">
                <img src="${sessionScope.account.getImage()}">
            </a>
        </c:if>
    </header>
</html>
