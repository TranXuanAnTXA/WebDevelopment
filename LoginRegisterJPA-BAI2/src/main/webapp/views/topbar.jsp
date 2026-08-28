<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">  

<div class="container-fluid bg-dark py-3">
    <div class="container">

        <div class="d-flex justify-content-end align-items-center text-white">

            <c:choose>

                <%-- Chưa đăng nhập --%>
                <c:when test="${sessionScope.account == null}">

                    <a href="${pageContext.request.contextPath}/login"
                       class="text-white text-decoration-none me-3">
                        Đăng nhập
                    </a>

                    <span class="me-3">|</span>

                    <a href="${pageContext.request.contextPath}/register"
                       class="text-white text-decoration-none">
                        Đăng ký
                    </a>

                </c:when>


                <%-- Đã đăng nhập --%>
                <c:otherwise>

                    <span class="me-3">
                        Xin chào,
                        <strong>${sessionScope.account.fullName}</strong>
                    </span>

                    <span class="me-3">|</span>

                    <a href="${pageContext.request.contextPath}/member/myaccount"
                       class="text-white text-decoration-none me-3">
                        Tài khoản
                    </a>

                    <span class="me-3">|</span>

                    <a href="${pageContext.request.contextPath}/logout"
                       class="text-white text-decoration-none">
                        Đăng xuất
                    </a>

                </c:otherwise>

            </c:choose>

        </div>

    </div>
</div>