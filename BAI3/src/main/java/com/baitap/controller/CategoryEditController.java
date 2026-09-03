package com.baitap.controller;

import java.io.File;
import java.io.IOException;

import com.baitap.model.Category;
import com.baitap.service.CategoryService;
import com.baitap.serviceimpl.CategoryServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/admin/category/edit")
@MultipartConfig
public class CategoryEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryService cateService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        String id =
                req.getParameter("id");

        try {

            int categoryId =
                    Integer.parseInt(id);

            Category category =
                    cateService.get(categoryId);

            if (category == null) {

                resp.sendError(
                        HttpServletResponse.SC_NOT_FOUND,
                        "Không tìm thấy Category"
                );

                return;
            }

            req.setAttribute(
                    "category",
                    category
            );

            req.getRequestDispatcher(
                    "/views/Category/edit-category.jsp"
            ).forward(req, resp);

        } catch (NumberFormatException e) {

            resp.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "ID Category không hợp lệ"
            );
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        try {

            // =========================
            // 1. Lấy ID
            // =========================

            int id =
                    Integer.parseInt(
                            req.getParameter("id")
                    );

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
            // 3. Update name
            // =========================

            String name =
                    req.getParameter("name");

            category.setName(name);

            // =========================
            // 4. Upload icon mới
            // =========================

            Part iconPart =
                    req.getPart("icon");

            if (iconPart != null
                    && iconPart.getSize() > 0) {

                String originalFileName =
                        iconPart.getSubmittedFileName();

                String extension = "";

                if (originalFileName != null) {

                    int index =
                            originalFileName.lastIndexOf(".");

                    if (index >= 0) {
                        extension =
                                originalFileName.substring(index);
                    }
                }

                String fileName =
                        System.currentTimeMillis()
                        + extension;

                String uploadPath =
                        getServletContext()
                        .getRealPath(
                                "/images/category"
                        );

                File uploadDir =
                        new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                File file =
                        new File(
                                uploadDir,
                                fileName
                        );

                iconPart.write(
                        file.getAbsolutePath()
                );

                category.setIcon(
                        "images/category/"
                        + fileName
                );
            }

            // =========================
            // 5. UPDATE
            // =========================

            cateService.edit(category);

            // =========================
            // 6. Redirect
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
                    "Có lỗi khi cập nhật Category"
            );
        }
    }
}