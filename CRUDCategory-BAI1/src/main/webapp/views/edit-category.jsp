<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Category</title>

    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .container {
            width: 500px;
            margin: 50px auto;
        }

        h2 {
            text-align: center;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="file"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        .current-image {
            margin-top: 10px;
            margin-bottom: 15px;
        }

        .current-image img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border: 1px solid #ccc;
        }

        .buttons {
            margin-top: 20px;
        }

        button,
        a {
            padding: 8px 15px;
            text-decoration: none;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Sửa Category</h2>

    <form
        action="${pageContext.request.contextPath}/admin/category/edit"
        method="post"
        enctype="multipart/form-data">

        <!-- ID -->
        <input
            type="hidden"
            name="id"
            value="${category.id}">

        <!-- Tên Category -->
        <div class="form-group">

            <label for="name">
                Tên Category:
            </label>

            <input
                type="text"
                id="name"
                name="name"
                value="${category.name}"
                required>

        </div>

        <!-- Ảnh hiện tại -->
        <div class="form-group">

            <label>
                Icon hiện tại:
            </label>

            <div class="current-image">

                <c:choose>

                    <c:when test="${not empty category.icon}">

                        <img
                            src="${pageContext.request.contextPath}/${category.icon}"
                            alt="${category.name}">

                    </c:when>

                    <c:otherwise>

                        <p>Chưa có icon</p>

                    </c:otherwise>

                </c:choose>

            </div>

        </div>

        <!-- Chọn ảnh mới -->
        <div class="form-group">

            <label for="icon">
                Chọn icon mới:
            </label>

            <input
                type="file"
                id="icon"
                name="icon"
                accept="image/*">

            <small>
                Nếu không chọn ảnh mới, icon hiện tại sẽ được giữ nguyên.
            </small>

        </div>

        <!-- Buttons -->
        <div class="buttons">

            <button type="submit">
                Cập nhật
            </button>

            <a href="${pageContext.request.contextPath}/admin/category/list">
                Quay lại
            </a>

        </div>

    </form>

</div>

</body>
</html>