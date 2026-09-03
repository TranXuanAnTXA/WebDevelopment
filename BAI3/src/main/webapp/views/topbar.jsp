<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">

    <div class="container">

        <!-- Logo -->
        <a class="navbar-brand fw-bold"
           href="${pageContext.request.contextPath}/home">
            🛒 PRODUCT SERVICE
        </a>

        <!-- Menu mobile -->
        <button class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#mainNavbar"
                aria-controls="mainNavbar"
                aria-expanded="false"
                aria-label="Toggle navigation">

            <span class="navbar-toggler-icon"></span>

        </button>

        <div class="collapse navbar-collapse" id="mainNavbar">

            <!-- Menu chính -->
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/home">
                        Trang chủ
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/product">
                        Sản phẩm
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/admin/category/list">
                        Danh mục
                    </a>
                </li>

            </ul>

            <!-- Tài khoản -->
            <div class="d-flex align-items-center text-white">

                <c:choose>

                    <c:when test="${sessionScope.account == null}">

                        <a href="${pageContext.request.contextPath}/login"
                           class="btn btn-outline-light btn-sm me-2">
                            Đăng nhập
                        </a>

                        <a href="${pageContext.request.contextPath}/register"
                           class="btn btn-primary btn-sm">
                            Đăng ký
                        </a>

                    </c:when>

                    <c:otherwise>

                        <span class="me-3">
                            Xin chào,
                            <strong>
                                ${sessionScope.account.fullName}
                            </strong>
                        </span>

                        <a href="${pageContext.request.contextPath}/profile"
                           class="btn btn-outline-light btn-sm me-2">
                            Tài khoản
                        </a>

                        <a href="${pageContext.request.contextPath}/logout"
                           class="btn btn-danger btn-sm">
                            Đăng xuất
                        </a>

                    </c:otherwise>

                </c:choose>

            </div>

        </div>

    </div>

</nav>