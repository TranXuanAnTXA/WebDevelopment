<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Trang chủ</title>

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        rel="stylesheet">

</head>


<body class="bg-light">

    <%-- Thanh menu dùng chung --%>
    <%@ include file="topbar.jsp" %>


    <div class="container text-center mt-5">

        <h1 class="display-5 text-primary mb-4 fw-bold">
            Trang Chủ
        </h1>


        <%-- Hiển thị khi đăng nhập thành công --%>
        <c:if test="${not empty message}">
            <div class="alert alert-success d-inline-block shadow-sm px-5"
                 role="alert">

                <strong>Tuyệt vời!</strong>
                ${message}

            </div>
        </c:if>


        <c:choose>

            <c:when test="${sessionScope.account != null}">

                <div class="mt-4">

                    <h3>
                        Xin chào,
                        ${sessionScope.account.fullName}
                    </h3>

                    <p class="text-muted">
                        Bạn đã đăng nhập thành công.
                    </p>

                    <p>
                        Username:
                        <strong>
                            ${sessionScope.account.userName}
                        </strong>
                    </p>

                    <p>
                        Role ID:
                        <strong>
                            ${sessionScope.account.roleid}
                        </strong>
                    </p>

                </div>

            </c:when>


            <c:otherwise>

                <div class="mt-4">

                    <p class="text-muted">
                        Bạn chưa đăng nhập.
                    </p>

                    <a
                        href="${pageContext.request.contextPath}/login"
                        class="btn btn-primary btn-lg px-5 shadow-sm">
                        Đi tới Đăng nhập
                    </a>

                    <a
                        href="${pageContext.request.contextPath}/register"
                        class="btn btn-outline-primary btn-lg px-5 shadow-sm ms-2">
                        Đăng ký
                    </a>

                </div>

            </c:otherwise>

        </c:choose>

    </div>

</body>
</html>