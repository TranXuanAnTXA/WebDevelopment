<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Thêm danh mục</title>

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

                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">
                            Thêm danh mục
                        </h4>
                    </div>


                    <div class="card-body p-4">

                        <form
                            action="${pageContext.request.contextPath}/admin/category/add"
                            method="post"
                            enctype="multipart/form-data">


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
                                    class="form-control"
                                    placeholder="Nhập tên danh mục"
                                    required>

                            </div>


                            <div class="mb-4">

                                <label
                                    for="icon"
                                    class="form-label fw-bold">

                                    Icon

                                </label>

                                <input
                                    type="file"
                                    id="icon"
                                    name="icon"
                                    class="form-control"
                                    accept="image/*">

                                <div class="form-text">
                                    Chọn hình ảnh làm icon cho danh mục.
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
                                    class="btn btn-primary">

                                    Thêm danh mục

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