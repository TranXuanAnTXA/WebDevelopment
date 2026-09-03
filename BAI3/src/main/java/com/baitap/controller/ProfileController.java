package com.baitap.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import com.baitap.model.User;
import com.baitap.service.UserService;
import com.baitap.serviceimpl.UserServiceImpl;
import com.baitap.utils.UploadUtil;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 5 * 1024 * 1024,
    maxRequestSize = 10 * 1024 * 1024
)
@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();

    // Hiển thị Profile
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user =
                (User) session.getAttribute("account");

        // Chưa đăng nhập
        if (user == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        request.setAttribute("user", user);

        request.getRequestDispatcher(
                "/views/profile.jsp"
        ).forward(request, response);
    }


    // Cập nhật Profile
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        User user =
                (User) session.getAttribute("account");

        // Chưa đăng nhập
        if (user == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        // Lấy dữ liệu từ form
        String fullName =
                request.getParameter("fullName");

        String phone =
                request.getParameter("phone");

        // Nhận file
        Part avatarPart =
                request.getPart("avatar");

        // Mặc định giữ avatar cũ
        String avatarPath =
                user.getAvatar();

        // Nếu user chọn ảnh mới
        if (avatarPart != null
                && avatarPart.getSize() > 0) {

            String uploadFolder =
                    getServletContext()
                            .getRealPath(
                                    "/uploads/avatar"
                            );

            String uploadedPath =
                    UploadUtil.uploadFile(
                            avatarPart,
                            uploadFolder,
                            "uploads/avatar"
                    );

            if (uploadedPath != null) {
                avatarPath = uploadedPath;
            }
        }

        // Cập nhật object User
        user.setFullName(fullName);
        user.setPhone(phone);
        user.setAvatar(avatarPath);

        // Gọi Service → DAO → JPA
        boolean result =
                userService.updateProfile(user);

        if (result) {

            // Cập nhật User trong Session
            session.setAttribute(
                    "account",
                    user
            );

            // Quay lại Profile
            response.sendRedirect(
                    request.getContextPath()
                            + "/profile"
            );

        } else {

            request.setAttribute(
                    "error",
                    "Cập nhật thông tin thất bại!"
            );

            request.setAttribute(
                    "user",
                    user
            );

            request.getRequestDispatcher(
                    "/views/profile.jsp"
            ).forward(request, response);
        }
    }
}