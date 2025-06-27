<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Fake Restaurant</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleHome.css">
        <jsp:include page="/Views/Header.jsp" />
    </head>
    <body>

        <!-- ✅ Sản phẩm bán chạy nhất -->
        <section class="menu-section">
            <h2>🔥 Sản Phẩm Bán Chạy Nhất</h2>
            <div class="product-grid">
                <c:forEach items="${sessionScope.bestSellerProducts}" var="b">
                    <div class="card">
                        <img src="${b.image != null ? b.image : b.getImage()}" class="product-img" alt="${b.name != null ? b.name : b.getName()}">
                        <div class="card-content">
                            <h3>${b.name != null ? b.name : b.getName()}</h3>
                            <p>${b.description != null ? b.description : b.getDescription()}</p>
                            <div class="card-bottom">
                                <span>${b.price != null ? b.price : b.getPrice()}$</span>
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
                    <button 
                        <c:if test="${sessionScope.cateChoice == null || sessionScope.cateChoice == 0}">class="active"</c:if> 
                        name="categorySearch" value="all">Tất cả</button>

                    <c:forEach items="${sessionScope.category}" var="c">
                        <button 
                            <c:if test="${sessionScope.cateChoice == c.categoryID || sessionScope.cateChoice == c.getCategoryID()}">class="active"</c:if> 
                            name="categorySearch" value="${c.categoryID != null ? c.categoryID : c.getCategoryID()}">
                            ${c.name != null ? c.name : c.getName()}
                        </button>
                    </c:forEach>
                </form>
            </div>

            <div class="product-grid">
                <c:forEach items="${sessionScope.product}" var="c">
                    <div class="card">
                        <img src="${c.image != null ? c.image : c.getImage()}" class="product-img" alt="${c.name != null ? c.name : c.getName()}">
                        <div class="card-content">
                            <h3>${c.name != null ? c.name : c.getName()}</h3>
                            <p>${c.description != null ? c.description : c.getDescription()}</p>
                            <div class="card-bottom">
                                <span>${c.price != null ? c.price : c.getPrice()}$</span>
                                <button class="cart-btn">🛒</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Pagination hoặc nút Xem thêm -->
            <c:if test="${not empty sessionScope.page}">
                <div class="pagination">
                    <c:forEach items="${sessionScope.page}" var="c">
                        <a href="${pageContext.request.contextPath}/Home?pageNum=${c.currentPage}" 
                           <c:if test="${sessionScope.pageCurrent == c.currentPage}">style="background-color: grey"</c:if>>
                            ${c.currentPage}
                        </a>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.page}">
                <button class="view-more">Xem Thêm</button>
            </c:if>
        </section>

        <!-- ✅ Footer -->
        <jsp:include page="/Views/Footer.jsp" />
    </body>
</html>
