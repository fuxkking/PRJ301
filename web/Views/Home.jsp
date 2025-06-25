<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Thực Đơn Feane</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleHome.css">
    </head>
    <body>

        <!-- ✅ Thanh điều hướng -->
        <header class="navbar">
            <div class="logo">Feane</div>
            <nav class="nav-links">
                <a href="#">TRANG CHỦ</a>
                <a href="#">THỰC ĐƠN</a>
                <a href="#">GIỚI THIỆU</a>
                <a href="#">ĐẶT BÀN</a>
            </nav>
            <c:if test="${sessionScope.account == null}">
                <button class="log-resgister">
                    <a href="Login.jsp">Login </a>|
                    <a href="Register.jsp">Sign Up</a>
                </button>
            </c:if>
            <c:if test="${sessionScope.account != null}">
                <button class="User">
                    <img src="${account.getImage()}">
                </button>
            </c:if>

        </header>

        <!-- ✅ Khu vực thực đơn -->
        <section class="menu-section">
            <h2>Thực Đơn Của Chúng Tôi</h2>

            <!-- ✅ Thanh tìm kiếm -->
            <div class="search-bar">
                <input type="text" placeholder="🔍 Tìm món ăn... (không hoạt động)">
            </div>

            <!-- ✅ Bộ lọc món ăn -->
            <div class="filters">
                <button class="active">Tất cả</button>
                <button>Burger</button>
                <button>Pizza</button>
                <button>Mì Ý</button>
                <button>Khoai Tây</button>
            </div>

            <!-- ✅ Danh sách món ăn -->
            <div class="product-grid">
                <div class="card">
                    <img src="https://source.unsplash.com/100x100/?pizza" class="product-img" alt="Pizza">
                    <div class="card-content">
                        <h3>Pizza Thơm Ngon</h3>
                        <p>Đế mỏng, phô mai tan chảy và topping đa dạng.</p>
                        <div class="card-bottom">
                            <span>20$</span>
                            <button class="cart-btn">🛒</button>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <img src="https://dynamic-media-cdn.tripadvisor.com/media/photo-o/27/e1/59/85/our-combo-3-2-x-sliders.jpg" class="product-img" alt="Burger">
                    <div class="card-content">
                        <h3>Burger Bò</h3>
                        <p>Thịt bò nướng, xà lách, cà chua và phô mai.</p>
                        <div class="card-bottom">
                            <span>15$</span>
                            <button class="cart-btn">🛒</button>
                        </div>
                    </div>
                </div>

                <div class="card">
                    <img src="https://source.unsplash.com/100x100/?pasta" class="product-img" alt="Pasta">
                    <div class="card-content">
                        <h3>Mì Ý Kem</h3>
                        <p>Sốt kem đậm đà, mì ống kiểu Ý truyền thống.</p>
                        <div class="card-bottom">
                            <span>17$</span>
                            <button class="cart-btn">🛒</button>
                        </div>
                    </div>
                </div>

                <!-- Có thể thêm nhiều món khác tương tự -->
            </div>

            <button class="view-more">Xem Thêm</button>
        </section>

    </body>
</html>
