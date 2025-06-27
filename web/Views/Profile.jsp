    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Kiểm tra đăng nhập --%>
<c:if test="${sessionScope.account == null}">
    <c:redirect url="${pageContext.request.contextPath}/Views/Login.jsp"/>
</c:if>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Profile</title>
                <jsp:include page="/Views/Header.jsp" />
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleHome.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
    </head>
    <body>
        <div class="profile-main" style="justify-content:center;">
            <div class="profile-card" style="min-width:400px;max-width:600px;width:100%;margin:auto;">
                <form class="profile-form" action="${pageContext.request.contextPath}/User/Update" method="post">
                    <img class="profile-avatar" src="${sessionScope.account.image}" alt="Avatar">
                    <label>Username</label>
                    <input type="text" name="username" value="${sessionScope.account.getUsername()}" disabled>
                    <label>Email</label>
                    <input type="email" name="email" value="${sessionScope.account.getEmail()}" disabled>
                    <label>Phone</label>
                    <input type="text" name="phone" value="${sessionScope.account.getPhone()}" disabled>
                    <label>Avatar URL</label>
                    <input type="text" name="image" value="${sessionScope.account.getImage()}">
                    <button class="profile-btn" type="submit">Cập nhật</button>
                </form>
                <form action="${pageContext.request.contextPath}/Login" method="post">
                    <button class="profile-btn-outline" type="submit" name="type" value="logout">Đăng xuất</button>
                </form>
            </div>
        </div>
    </body>
</html> 