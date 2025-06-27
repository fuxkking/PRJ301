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
        <%
        
        %>

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
                    <a href="${pageContext.request.contextPath}/Views/Login.jsp">Login</a> |
                    <a href="${pageContext.request.contextPath}/Views/Register.jsp">Sign Up</a>
                </button>
            </c:if>
            <c:if test="${sessionScope.account != null}">
                <button class="User">
                    <img src="${sessionScope.account.image}">
                </button>
            </c:if>
        </header>

        <!-- ✅ Sản phẩm bán chạy nhất -->
        <section class="menu-section">
            <h2>🔥 Sản Phẩm Bán Chạy Nhất</h2>
            <div class="product-grid">
                <c:forEach items="${sessionScope.bestSellerProducts}" var="b">
                    <div class="card">
                        <img src="${b.image}" class="product-img" alt="${b.name}">
                        <div class="card-content">
                            <h3>${b.name}</h3>
                            <p>${b.description}</p>
                            <div class="card-bottom">
                                <span>${b.price}$</span>
                                <button class="cart-btn">🛒</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

        <!-- ✅ Thực đơn -->
        <section class="menu-section">
            <h2>Thực Đơn Của Chúng Tôi</h2>
            <form action="${pageContext.request.contextPath}/Search" method="post">
                <div class="search-bar">
                    <input type="text" name="search-product" value="${productname}" placeholder="🔍 Tìm món ăn... (không hoạt động)">
                </div>
            </form>

            <div class="filters">
                <form action="${pageContext.request.contextPath}/Home" method="post">
                    <button <c:if test="${sessionScope.cateChoice == 0}">class="active"</c:if>
                                                                         name="categorySearch" value="all">Tất cả</button>
                    <c:forEach items="${sessionScope.category}" var="c">
                        <button <c:if test="${sessionScope.cateChoice == c.categoryID}">class="active"</c:if>
                                                                                        name="categorySearch" value="${c.categoryID}">${c.name}</button>
                    </c:forEach>
                </form>
            </div>


            <div class="product-grid">
                <c:forEach items="${sessionScope.product}" var="c">
                    <div class="card">
                        <img src="${c.image}" class="product-img" alt="${c.name}">
                        <div class="card-content">
                            <h3>${c.name}</h3>
                            <p>${c.description}</p>
                            <div class="card-bottom">
                                <span>${c.price}$</span>
                                <button class="cart-btn">🛒</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="pagination">
                <c:forEach items="${sessionScope.page}" var="c">
                    <a href="${pageContext.request.contextPath}/Home?pageNum=${c.currentPage}" 
                       <c:if test="${sessionScope.pageCurrent == c.currentPage}">style="background-color: grey"</c:if>>
                        ${c.currentPage}
                    </a>
                </c:forEach>
            </div>

        </section>

    </body>
</html>