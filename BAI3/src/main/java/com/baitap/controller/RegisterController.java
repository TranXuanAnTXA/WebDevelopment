package com.baitap.controller;

import java.io.IOException;

import com.baitap.constant.Constant;
import com.baitap.service.UserService;
import com.baitap.serviceimpl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // Kiểm tra Session
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        // Kiểm tra Cookie
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals(Constant.COOKIE_REMEMBER)) {

                    session = req.getSession(true);

                    session.setAttribute(
                        "username",
                        cookie.getValue()
                    );

                    resp.sendRedirect(
                        req.getContextPath() + "/waiting"
                    );

                    return;
                }
            }
        }

        // Chưa đăng nhập → hiển thị trang đăng ký
        req.getRequestDispatcher(
            Constant.Path.REGISTER
        ).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        UserService service = new UserServiceImpl();

        String alertMsg = "";

        // Kiểm tra email
        if (service.checkExistEmail(email)) {

            alertMsg = "Email đã tồn tại!";

            req.setAttribute("alert", alertMsg);

            req.getRequestDispatcher(
                Constant.Path.REGISTER
            ).forward(req, resp);

            return;
        }

        // Kiểm tra username
        if (service.checkExistUsername(username)) {

            alertMsg = "Tài khoản đã tồn tại!";

            req.setAttribute("alert", alertMsg);

            req.getRequestDispatcher(
                Constant.Path.REGISTER
            ).forward(req, resp);

            return;
        }

        // Thực hiện đăng ký
        boolean isSuccess =
                service.register(
                    username,
                    password,
                    email,
                    fullname,
                    phone
                );

        if (isSuccess) {

            HttpSession session = req.getSession();

            session.setAttribute(
                "registerSuccess",
                "Đăng ký thành công! Vui lòng đăng nhập."
            );

            resp.sendRedirect(req.getContextPath() + "/login");

        } else {

            alertMsg = "Đăng ký thất bại!";

            req.setAttribute("alert", alertMsg);

            req.getRequestDispatcher(Constant.Path.REGISTER)
               .forward(req, resp);
        }
    }
}