<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Lỗi Đăng Nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light pt-5">
    <div class="container text-center mt-5">
        <div class="alert alert-danger d-inline-block p-5 shadow-sm text-center" role="alert">
            <h2 class="alert-heading fw-bold mb-3">Đăng nhập thất bại!</h2>
            <p class="fs-5">Sai tài khoản hoặc mật khẩu. Vui lòng kiểm tra lại.</p>
            <hr class="my-4">
            <a href="${pageContext.request.contextPath}/login" class="btn btn-danger btn-lg px-4">
                Quay lại trang Đăng nhập
            </a>
        </div>
    </div>
</body>
</html>