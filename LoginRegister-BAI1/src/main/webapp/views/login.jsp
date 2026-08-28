<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="vi">

<head>
<meta charset="UTF-8">

<title>Đăng nhập</title>

<!-- Nhúng CSS Bootstrap 5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body class="bg-light d-flex align-items-center vh-100">

	<!-- Căn giữa màn hình -->
	<div class="container">

		<div class="row justify-content-center">

			<div class="col-md-4">

				<div class="card shadow-sm">

					<div class="card-header bg-primary text-white text-center py-3">
						<h4 class="mb-0">Đăng Nhập Hệ Thống</h4>
					</div>

					<div class="card-body p-4">
						<c:if test="${not empty alert}">
							<div class="alert alert-danger mb-3" role="alert">${alert}
							</div>
						</c:if>
						<c:if test="${not empty sessionScope.registerSuccess}">

							<div class="alert alert-success mb-3" role="alert">
								${sessionScope.registerSuccess}</div>

							<%
							session.removeAttribute("registerSuccess");
							%>

						</c:if>
						<form action="${pageContext.request.contextPath}/login"
							method="post">

							<!-- Username -->
							<div class="mb-3">
								<label class="form-label fw-bold"> Tên đăng nhập </label> <input
									type="text" name="username" class="form-control"
									/>
							</div>

							<!-- Password -->
							<div class="mb-3">
								<label class="form-label fw-bold"> Mật khẩu </label> <input
									type="password" name="password" class="form-control"
									 />
							</div>

							<!-- Remember Me -->
							<div class="form-check mb-3">

								<input class="form-check-input" type="checkbox" name="remember"
									id="remember"> <label class="form-check-label"
									for="remember"> Ghi nhớ đăng nhập </label>

							</div>

							<!-- Nút đăng nhập -->
							<button type="submit" class="btn btn-primary w-100 mt-2">
								Đăng nhập</button>

						</form>

					</div>

				</div>

			</div>

		</div>

	</div>

</body>
</html>