<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Category List</title>

    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        img {
            width: 80px;
            height: 80px;
            object-fit: cover;
        }

        .container {
            text-align: center;
        }

        a {
            text-decoration: none;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Danh sách Category</h2>

    <a href="${pageContext.request.contextPath}/admin/category/add">
        Thêm Category
    </a>

    <br><br>

    <table>

        <thead>
            <tr>
                <th>ID</th>
                <th>Tên Category</th>
                <th>Icon</th>
                <th>Chức năng</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach var="category" items="${categories}">

                <tr>

                    <!-- ID -->
                    <td>
                        ${category.id}
                    </td>

                    <!-- Name -->
                    <td>
                        ${category.name}
                    </td>

                    <!-- Icon -->
                    <td>

                        <c:if test="${not empty category.icon}">

                            <img
                                src="${pageContext.request.contextPath}/${category.icon}"
                                alt="${category.name}">

                        </c:if>

                        <c:if test="${empty category.icon}">
                            Không có ảnh
                        </c:if>

                    </td>

                    <!-- Action -->
                    <td>

                        <a href="${pageContext.request.contextPath}/admin/category/edit?id=${category.id}">
                            Sửa
                        </a>

                        &nbsp; | &nbsp;

                        <a
                            href="${pageContext.request.contextPath}/admin/category/delete?id=${category.id}"
                            onclick="return confirm('Bạn có chắc muốn xóa Category này không?');">
                            Xóa
                        </a>

                    </td>

                </tr>

            </c:forEach>

        </tbody>

    </table>

</div>

</body>
</html>