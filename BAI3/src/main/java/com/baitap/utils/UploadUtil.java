package com.baitap.utils;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class UploadUtil {

    public static String uploadFile(
            Part filePart,
            String uploadFolder,
            String webPath) throws IOException {

        // Không có file
        if (filePart == null || filePart.getSize() == 0) {
            return null;
        }

        // Lấy tên file
        String originalFileName = Paths
                .get(filePart.getSubmittedFileName())
                .getFileName()
                .toString();

        // Tạo tên file mới để tránh trùng
        String fileName =
                System.currentTimeMillis()
                + "_"
                + originalFileName;

        // Đường dẫn thật trên server
        String uploadPath = uploadFolder;

        File uploadDir = new File(uploadPath);

        // Nếu thư mục chưa tồn tại thì tạo
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Đường dẫn lưu file
        String filePath =
                uploadPath
                + File.separator
                + fileName;

        // Lưu file
        filePart.write(filePath);

        // Trả về đường dẫn để lưu database
        return webPath + "/" + fileName;
    }
}