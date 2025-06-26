<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Admin - Fake Restaurant</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styleAdmin.css">
        <script src="${pageContext.request.contextPath}/Js/OpenModal.js"></script>
    </head>
    <body>
        <header class="navbar">
            <div class="logo">Fake Restaurant</div>
            <nav class="nav-links">
                <a href="${pageContext.request.contextPath}/Admin/Admin.jsp">TRANG CHỦ</a>
                <a href="#">QUẢN LÝ SẢN PHẨM</a>
                <a href="#">QUẢN LÝ DANH MỤC</a>
                <a href="#">QUẢN LÝ ĐƠN HÀNG</a>
                <a href="#">QUẢN LÝ NGƯỜI DÙNG</a>
            </nav>
            <c:if test="${sessionScope.account != null}">
                <button class="admin-info">
                    <img src="${account.getImage()}">
                </button>
            </c:if>
        </header>

        <section class="admin-section">
            <h2>Quản Lý Sản Phẩm</h2>
            <button class="add-btn" id="openAddModal" onclick="openModal()">+ Thêm Sản Phẩm</button>
            <div id="addProductModal" class="modal">
                <div class="modal-content">
                    <span class="close" id="closeAddModal" onclick="closeAddModal()">&times;</span>
                    <h2>Thêm Sản Phẩm Mới</h2>
                    <form action="${pageContext.request.contextPath}/AddProduct" method="post">
                        <div class="form-group">
                            <label for="name">Tên sản phẩm</label>
                            <input type="text" id="name" name="name" required placeholder="Nhập tên sản phẩm">
                        </div>
                        <div class="form-group">
                            <label for="description">Mô tả</label>
                            <textarea id="description" name="description" required placeholder="Nhập mô tả"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="price">Giá</label>
                            <input type="number" id="price" name="price" min="0" step="0.01" required placeholder="Nhập giá">
                        </div>
                        <div class="form-group">
                            <label for="image">Link ảnh</label>
                            <input type="text" id="image" name="image" required placeholder="Nhập URL ảnh">
                        </div>
                        <div class="form-group">
                            <label for="category">CategoryID</label>
                            <select id="category" name="categoryID" required>
                                <option value="">-- Chọn CategoryID --</option>
                                <c:forEach items="${sessionScope.category}" var="c">
                                    <option value="${c.getCategoryID()}">${c.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-actions">
                            <button type="submit">Thêm sản phẩm</button>
                        </div>
                    </form>
                </div>
            </div>
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>Ảnh</th>
                        <th>Tên</th>
                        <th>Mô tả</th>
                        <th>Giá</th>
                        <th>Danh mục</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.product}" var="p">
                        <tr>
                            <td><img src="${p.getImage()}" alt="${p.getName()}" width="60"></td>
                            <td>${p.getName()}</td>
                            <td>${p.getDescription()}</td>
                            <td>${p.getPrice()}$</td>
                            <td>${p.getCategory().getName()}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/EditProduct?id=${p.getProductID()}" class="edit-btn">Sửa</a>
                                <a href="${pageContext.request.contextPath}/DeleteProduct?id=${p.getProductID()}" class="delete-btn" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>


    </body>
</html>
