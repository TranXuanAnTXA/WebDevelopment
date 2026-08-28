<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
    <!-- Nhúng CSS Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex align-items-center py-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card shadow-sm">
                    <div class="card-header bg-success text-white text-center py-3">
                        <h4 class="mb-0">Tạo Tài Khoản Mới</h4>
                    </div>
                    <div class="card-body p-4">
                        
                        <%-- Khối hiển thị thông báo lỗi (alert) từ Controller gửi sang --%>
                        <c:if test="${alert != null}">
                            <div class="alert alert-danger text-center" role="alert">
                                <strong>Lỗi!</strong> ${alert}
                            </div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/register" method="post">
                            <div class="mb-3">
                                <label class="form-label fw-bold">Họ và tên</label>
                                <input type="text" name="fullname" class="form-control" placeholder="Nhập họ và tên của bạn" required />
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label fw-bold">Email</label>
                                <input type="email" name="email" class="form-control" placeholder="example@email.com" required />
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label fw-bold">Số điện thoại</label>
                                <input type="text" name="phone" class="form-control" placeholder="Nhập số điện thoại" required />
                            </div>

                            <div class="mb-3">
                                <label class="form-label fw-bold">Tên đăng nhập</label>
                                <input type="text" name="username" class="form-control" placeholder="Tên dùng để đăng nhập" required />
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label fw-bold">Mật khẩu</label>
                                <input type="password" name="password" class="form-control" placeholder="Tạo mật khẩu" required />
                            </div>
                            
                            <button type="submit" class="btn btn-success w-100 mt-3 fw-bold">Đăng ký ngay</button>
                        </form>
                        
                        <div class="text-center mt-3">
                            <span>Nếu bạn đã có tài khoản? </span>
                            <a href="${pageContext.request.contextPath}/login" class="text-decoration-none fw-bold">Đăng nhập</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>