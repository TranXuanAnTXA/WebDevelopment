package com.baitap.controller;

import java.io.IOException;

import com.baitap.service.CategoryService;
import com.baitap.serviceimpl.CategoryServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/category/delete")
public class CategoryDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryService cateService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            int id =
                    Integer.parseInt(
                            req.getParameter("id")
                    );

            cateService.delete(id);

            resp.sendRedirect(
                    req.getContextPath()
                    + "/admin/category/list"
            );

        } catch (NumberFormatException e) {

            resp.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "ID Category không hợp lệ"
            );

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Không thể xóa Category"
            );
        }
    }
}