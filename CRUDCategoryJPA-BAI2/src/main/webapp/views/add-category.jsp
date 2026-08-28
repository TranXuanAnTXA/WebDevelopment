<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Category</title>

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

    <h2>Thêm Category</h2>

    <form
        action="${pageContext.request.contextPath}/admin/category/add"
        method="post"
        enctype="multipart/form-data">

        <!-- Tên Category -->
        <div class="form-group">

            <label for="name">
                Tên Category:
            </label>

            <input
                type="text"
                id="name"
                name="name"
                required>

        </div>

        <!-- Icon -->
        <div class="form-group">

            <label for="icon">
                Icon:
            </label>

            <input
                type="file"
                id="icon"
                name="icon"
                accept="image/*">

        </div>

        <!-- Buttons -->
        <div class="buttons">

            <button type="submit">
                Thêm
            </button>

            <a href="${pageContext.request.contextPath}/admin/category/list">
                Quay lại
            </a>

        </div>

    </form>

</div>

</body>
</html>