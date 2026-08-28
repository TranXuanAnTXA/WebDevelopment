package com.baitap.controller;

import java.io.File;
import java.io.IOException;

import com.baitap.model.Category;
import com.baitap.service.CategoryService;
import com.baitap.serviceimpl.CategoryServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig
public class CategoryAddController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/views/add-category.jsp");

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        try {

            // =========================
            // 1. Tạo Category
            // =========================

            Category category = new Category();

            // =========================
            // 2. Lấy tên Category
            // =========================

            String name = req.getParameter("name");

            category.setName(name);

            // =========================
            // 3. Lấy file icon
            // =========================

            Part iconPart = req.getPart("icon");

            if (iconPart != null && iconPart.getSize() > 0) {

                // Lấy tên file gốc
                String originalFileName =
                        iconPart.getSubmittedFileName();

                // Lấy extension
                String extension = "";

                if (originalFileName != null) {

                    int index =
                            originalFileName.lastIndexOf(".");

                    if (index >= 0) {
                        extension =
                                originalFileName.substring(index + 1);
                    }
                }

                // Tạo tên file mới
                String fileName =
                        System.currentTimeMillis()
                        + (extension.isEmpty()
                            ? ""
                            : "." + extension);

                // =========================
                // 4. Lấy đường dẫn ứng dụng
                // =========================

                String uploadPath =
                        getServletContext()
                        .getRealPath("/images/category");

                File uploadDir =
                        new File(uploadPath);

                // Nếu thư mục chưa tồn tại thì tạo
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // =========================
                // 5. Tạo file
                // =========================

                File file =
                        new File(uploadDir, fileName);

                // Lưu file
                iconPart.write(file.getAbsolutePath());

                // Lưu đường dẫn vào database
                category.setIcon(
                        "images/category/" + fileName
                );
            }

            // =========================
            // 6. Insert database
            // =========================

            cateService.insert(category);

            // =========================
            // 7. Redirect
            // =========================

            resp.sendRedirect(
                    req.getContextPath()
                    + "/admin/category/list"
            );

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Có lỗi xảy ra khi thêm Category"
            );
        }
    }
}