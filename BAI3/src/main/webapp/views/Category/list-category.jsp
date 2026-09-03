<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">

<head>

<meta charset="UTF-8">

<title>Quản lý danh mục</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>

<body class="bg-light">

	<%@ include file="../topbar.jsp"%>


	<div class="container py-5">

		<!-- Tiêu đề -->
		<div class="d-flex justify-content-between align-items-center mb-4">

			<div>
				<h2 class="fw-bold mb-1">Quản lý danh mục</h2>

				<p class="text-muted mb-0">Danh sách các danh mục sản phẩm</p>
			</div>

			<a href="${pageContext.request.contextPath}/admin/category/add"
				class="btn btn-primary"> + Thêm danh mục </a>

		</div>


		<!-- Bảng -->
		<div class="card shadow-sm border-0">

			<div class="card-body p-0">

				<div class="table-responsive">

					<table class="table table-hover align-middle mb-0">

						<thead class="table-dark">

							<tr>
								<th class="text-center">ID</th>
								<th>Tên danh mục</th>
								<th class="text-center">Icon</th>
								<th class="text-center">Chức năng</th>
							</tr>

						</thead>


						<tbody>

							<c:forEach var="category" items="${categories}">

								<tr>

									<td class="text-center">${category.id}</td>

									<td class="fw-semibold">${category.name}</td>

									<td class="text-center"><c:choose>

											<c:when test="${not empty category.icon}">

												<img
													src="${pageContext.request.contextPath}/${category.icon}"
													alt="${category.name}" class="rounded"
													style="width: 70px; height: 70px; object-fit: cover;">

											</c:when>

											<c:otherwise>

												<span class="text-muted"> Không có ảnh </span>

											</c:otherwise>

										</c:choose></td>


									<td class="text-center"><a
										href="${pageContext.request.contextPath}/admin/category/edit?id=${category.id}"
										class="btn btn-warning btn-sm me-1"> Sửa </a> <a
										href="${pageContext.request.contextPath}/admin/category/delete?id=${category.id}"
										onclick="return confirm('Bạn có chắc muốn xóa Category này không?');"
										class="btn btn-danger btn-sm"> Xóa </a></td>

								</tr>

							</c:forEach>

						</tbody>

					</table>

				</div>

			</div>

		</div>

	</div>

</body>
</html>