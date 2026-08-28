package com.baitap.controller;

import java.io.IOException;

import com.baitap.model.User;
import com.baitap.service.UserService;
import com.baitap.serviceimpl.UserServiceImpl;
import com.baitap.constant.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // Kiểm tra session
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        // Kiểm tra cookie
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("username")) {

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

        // Chưa đăng nhập → chuyển tới login.jsp
        req.getRequestDispatcher("/views/login.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Kiểm tra Remember Me
        boolean isRememberMe = false;

        String remember = req.getParameter("remember");

        if ("on".equals(remember)) {
            isRememberMe = true;
        }

        String alertMsg = "";

        // Kiểm tra dữ liệu rỗng
        if (username.isEmpty() || password.isEmpty()) {

            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";

            req.setAttribute("alert", alertMsg);

            req.getRequestDispatcher("/views/login.jsp")
               .forward(req, resp);

            return;
        }

        // Gọi Service để đăng nhập
        UserService service = new UserServiceImpl();

        User user = service.login(username, password);

        // Đăng nhập thành công
        if (user != null) {

            HttpSession session = req.getSession(true);

            session.setAttribute("account", user);

            // Nếu chọn Remember Me
            if (isRememberMe) {
                saveRememberMe(resp, username);
            }

            // Chuyển sang trang waiting
            resp.sendRedirect(
                req.getContextPath() + "/waiting"
            );

        } else {

            // Đăng nhập thất bại
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";

            req.setAttribute("alert", alertMsg);

            req.getRequestDispatcher("/views/login.jsp")
               .forward(req, resp);
        }
    }

    // Tạo Cookie Remember Me
    private void saveRememberMe(
            HttpServletResponse response,
            String username) {

        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);

        cookie.setMaxAge(30 * 60);

        response.addCookie(cookie);
    }
    public static final String SESSION_USERNAME = "username";
    public static final String COOKIE_REMEMBER = "username";
}

