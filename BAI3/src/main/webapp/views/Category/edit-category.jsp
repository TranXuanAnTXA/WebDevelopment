<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Sửa danh mục</title>

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        rel="stylesheet">

</head>

<body class="bg-light">

    <%@ include file="../topbar.jsp" %>


    <div class="container py-5">

        <div class="row justify-content-center">

            <div class="col-md-6">

                <div class="card shadow-sm border-0">

                    <div class="card-header bg-warning">

                        <h4 class="mb-0">
                            Sửa danh mục
                        </h4>

                    </div>


                    <div class="card-body p-4">

                        <form
                            action="${pageContext.request.contextPath}/admin/category/edit"
                            method="post"
                            enctype="multipart/form-data">


                            <input
                                type="hidden"
                                name="id"
                                value="${category.id}">


                            <div class="mb-3">

                                <label
                                    for="name"
                                    class="form-label fw-bold">

                                    Tên danh mục

                                </label>

                                <input
                                    type="text"
                                    id="name"
                                    name="name"
                                    value="${category.name}"
                                    class="form-control"
                                    required>

                            </div>


                            <div class="mb-3">

                                <label class="form-label fw-bold">
                                    Icon hiện tại
                                </label>

                                <div>

                                    <c:choose>

                                        <c:when test="${not empty category.icon}">

                                            <img
                                                src="${pageContext.request.contextPath}/${category.icon}"
                                                alt="${category.name}"
                                                class="rounded border"
                                                style="width:120px;height:120px;object-fit:cover;">

                                        </c:when>

                                        <c:otherwise>

                                            <p class="text-muted">
                                                Chưa có icon
                                            </p>

                                        </c:otherwise>

                                    </c:choose>

                                </div>

                            </div>


                            <div class="mb-4">

                                <label
                                    for="icon"
                                    class="form-label fw-bold">

                                    Chọn icon mới

                                </label>

                                <input
                                    type="file"
                                    id="icon"
                                    name="icon"
                                    class="form-control"
                                    accept="image/*">

                                <div class="form-text">

                                    Nếu không chọn ảnh mới,
                                    icon hiện tại sẽ được giữ nguyên.

                                </div>

                            </div>


                            <div class="d-flex justify-content-between">

                                <a
                                    href="${pageContext.request.contextPath}/admin/category/list"
                                    class="btn btn-secondary">

                                    ← Quay lại

                                </a>


                                <button
                                    type="submit"
                                    class="btn btn-warning">

                                    Cập nhật

                                </button>

                            </div>

                        </form>

                    </div>

                </div>

            </div>

        </div>

    </div>

</body>
</html>