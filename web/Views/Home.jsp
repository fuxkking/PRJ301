<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Fake Restaurant</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleHome.css">
    </head>
    <body>

        <!-- ✅ Thanh điều hướng -->
        <header class="navbar">
            <div class="logo">Fake Restaurant</div>
            <nav class="nav-links">
                <a href="Views/Home.jsp">TRANG CHỦ</a>
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
                <button class="User">
                    <img src="${account.getImage()}">
                </button>
            </c:if>
        </header>

        <!-- ✅ Sản phẩm bán chạy nhất -->
        <section class="menu-section">
            <h2>🔥 Sản Phẩm Bán Chạy Nhất</h2>
            <div class="product-grid">
                <c:forEach items="${sessionScope.bestSellerProducts}" var="b">
                    <div class="card">
                        <img src="${b.getImage()}" class="product-img" alt="${b.getName()}">
                        <div class="card-content">
                            <h3>${b.getName()}</h3>
                            <p>${b.getDescription()}</p>
                            <div class="card-bottom">
                                <span>${b.getPrice()}$</span>
                                <button class="cart-btn">🛒</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

        <section class="menu-section">
            <h2>Thực Đơn Của Chúng Tôi</h2>
            <form action="Search" method="post">
                <div class="search-bar">
                    <input type="text" name="search-product"  value="${productname}" placeholder="🔍 Tìm món ăn... (không hoạt động)">
                </div>
            </form>
            <div class="filters">
                <form action="${pageContext.request.contextPath}/Home" method="post">
                    <button <c:if test="${cateChoice == null || cateChoice == 0}">class="active"</c:if> name="categorySearch" value="all">Tất cả</button>
                    <c:forEach items="${sessionScope.category}" var="c">
                        <button <c:if test="${cateChoice != null && cateChoice == c.getCategoryID()}">class="active"</c:if> value="${c.getCategoryID()}" name="categorySearch">${c.getName()}</button>
                    </c:forEach>
                </form>
            </div>

            <div class="product-grid">
                <c:forEach items="${sessionScope.product}" var="c">
                    <div class="card">
                        <img src=${c.getImage()} class="product-img" alt="${c.getName()}">
                        <div class="card-content">
                            <h3>${c.getName()}</h3>
                            <p>${c.getDescription()}</p>
                            <div class="card-bottom">
                                <span>${c.getPrice()}$</span>
                                <button class="cart-btn">🛒</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <button class="view-more">Xem Thêm</button>
        </section>

    </body>
</html>
