package com.baitap.controller;

import java.io.IOException;
import java.util.List;

import com.baitap.model.Category;
import com.baitap.service.CategoryService;
import com.baitap.serviceimpl.CategoryServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/admin/category/list" })
public class CategoryListController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Category> categories = cateService.getAll();

        req.setAttribute("categories", categories);

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/views/list-category.jsp");

        dispatcher.forward(req, resp);
    }
}