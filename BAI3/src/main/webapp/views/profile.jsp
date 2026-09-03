<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Tài khoản - PRODUCT SERVICE</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body class="bg-light">

	<%@ include file="/views/topbar.jsp"%>

	<c:if test="${not empty sessionScope.successMessage}">
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Thành công!</strong> ${sessionScope.successMessage}

			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>

		<c:remove var="successMessage" scope="session" />
	</c:if>
	<div class="container mt-5 mb-5">

		<div class="row justify-content-center">

			<div class="col-md-8 col-lg-6">

				<div class="card shadow-sm border-0">

					<!-- Header -->
					<div class="card-header bg-primary text-white text-center py-3">
						<h3 class="mb-0">👤 Tài khoản của tôi</h3>
					</div>


					<div class="card-body p-4">

						<form action="${pageContext.request.contextPath}/profile"
							method="post" enctype="multipart/form-data">


							<!-- Avatar -->
							<div class="text-center mb-4">

								<label class="form-label fw-bold d-block"> Ảnh đại diện
								</label>

								<c:choose>

									<c:when test="${not empty user.avatar}">

										<img src="${pageContext.request.contextPath}/${user.avatar}"
											alt="Avatar" class="rounded-circle border shadow-sm"
											style="width: 140px; height: 140px; object-fit: cover;">

									</c:when>

									<c:otherwise>

										<div
											class="rounded-circle bg-secondary text-white
                                                   d-flex align-items-center justify-content-center
                                                   mx-auto shadow-sm"
											style="width: 140px; height: 140px; font-size: 50px;">
											👤</div>

									</c:otherwise>

								</c:choose>

							</div>


							<!-- Full Name -->
							<div class="mb-3">

								<label for="fullName" class="form-label fw-bold"> Họ và
									tên </label> <input type="text" id="fullName" name="fullName"
									value="${user.fullName}" class="form-control"
									placeholder="Nhập họ và tên" required>

							</div>


							<!-- Phone -->
							<div class="mb-3">

								<label for="phone" class="form-label fw-bold"> Số điện
									thoại </label> <input type="text" id="phone" name="phone"
									value="${user.phone}" class="form-control"
									placeholder="Nhập số điện thoại" required>

							</div>


							<!-- Email -->
							<div class="mb-3">

								<label class="form-label fw-bold"> Email </label> <input
									type="text" value="${user.email}" class="form-control" readonly>

								<div class="form-text">Email không thể thay đổi.</div>

							</div>


							<!-- Username -->
							<div class="mb-3">

								<label class="form-label fw-bold"> Tên đăng nhập </label> <input
									type="text" value="${user.userName}" class="form-control"
									readonly>

								<div class="form-text">Tên đăng nhập không thể thay đổi.</div>

							</div>


							<!-- New Avatar -->
							<div class="mb-4">

								<label for="avatar" class="form-label fw-bold"> Chọn ảnh
									đại diện mới </label> <input type="file" id="avatar" name="avatar"
									class="form-control" accept="image/*">

								<div class="form-text">Chỉ chọn file hình ảnh.</div>

							</div>


							<!-- Buttons -->
							<div class="d-flex justify-content-between">

								<a href="${pageContext.request.contextPath}/home"
									class="btn btn-outline-secondary"> ← Quay lại </a>


								<button type="submit" class="btn btn-primary px-4">💾
									Cập nhật tài khoản</button>

							</div>

						</form>

					</div>

				</div>

			</div>

		</div>

	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
		
	</script>

</body>

</html>