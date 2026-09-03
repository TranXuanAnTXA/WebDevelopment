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

@WebServlet("/admin/category/add")
@MultipartConfig
public class CategoryAddController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryService cateService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(
                "/views/Category/add-category.jsp"
        ).forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        try {

            String name =
                    req.getParameter("name");

            Category category =
                    new Category();

            category.setName(name);

            // =========================
            // Upload icon
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
            // INSERT
            // =========================

            cateService.insert(category);

            // =========================
            // REDIRECT
            // =========================

            resp.sendRedirect(
                    req.getContextPath()
                    + "/admin/category/list"
            );

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendError(
                    HttpServletResponse
                            .SC_INTERNAL_SERVER_ERROR,
                    "Có lỗi khi thêm Category"
            );
        }
    }
}