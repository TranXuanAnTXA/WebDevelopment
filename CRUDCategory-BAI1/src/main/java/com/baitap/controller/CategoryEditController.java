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

@WebServlet(urlPatterns = { "/admin/category/edit" })
@MultipartConfig
public class CategoryEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Lấy ID từ URL
        String id = req.getParameter("id");

        // Lấy Category từ database
        Category category =
                cateService.get(Integer.parseInt(id));

        // Đưa Category sang JSP
        req.setAttribute("category", category);

        // Chuyển sang trang edit
        RequestDispatcher dispatcher =
                req.getRequestDispatcher(
                        "/views/edit-category.jsp"
                );

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        try {

            // =========================
            // 1. Lấy ID
            // =========================

            String idString =
                    req.getParameter("id");

            if (idString == null || idString.isEmpty()) {

                resp.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "ID Category không hợp lệ"
                );

                return;
            }

            int id =
                    Integer.parseInt(idString);

            // =========================
            // 2. Lấy Category cũ
            // =========================

            Category category =
                    cateService.get(id);

            if (category == null) {

                resp.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Không tìm thấy Category"
                );

                return;
            }

            // =========================
            // 3. Lấy tên mới
            // =========================

            String name =
                    req.getParameter("name");

            if (name != null) {
                category.setName(name);
            }

            // =========================
            // 4. Lấy icon mới
            // =========================

            Part iconPart =
                    req.getPart("icon");

            /*
             * Nếu người dùng chọn icon mới
             * thì upload icon mới.
             *
             * Nếu không chọn:
             * giữ nguyên icon cũ.
             */

            if (iconPart != null
                    && iconPart.getSize() > 0) {

                // Tên file gốc
                String originalFileName =
                        iconPart.getSubmittedFileName();

                // Extension
                String extension = "";

                if (originalFileName != null) {

                    int index =
                            originalFileName.lastIndexOf(".");

                    if (index >= 0) {

                        extension =
                                originalFileName.substring(
                                        index + 1
                                );
                    }
                }

                // Tạo tên file mới
                String fileName =
                        System.currentTimeMillis()
                        + (extension.isEmpty()
                            ? ""
                            : "." + extension);

                // =========================
                // 5. Lấy đường dẫn ứng dụng
                // =========================

                String uploadPath =
                        getServletContext()
                        .getRealPath("/images/category");

                File uploadDir =
                        new File(uploadPath);

                // Tạo thư mục nếu chưa có
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // =========================
                // 6. Tạo file
                // =========================

                File file =
                        new File(uploadDir, fileName);

                // Lưu file
                iconPart.write(
                        file.getAbsolutePath()
                );

                // Cập nhật đường dẫn icon
                category.setIcon(
                        "images/category/" + fileName
                );
            }

            // =========================
            // 7. Update database
            // =========================

            cateService.edit(category);

            // =========================
            // 8. Redirect
            // =========================

            resp.sendRedirect(
                    req.getContextPath()
                    + "/admin/category/list"
            );

        } catch (NumberFormatException e) {

            resp.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "ID không hợp lệ"
            );

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Có lỗi xảy ra khi cập nhật Category"
            );
        }
    }
}