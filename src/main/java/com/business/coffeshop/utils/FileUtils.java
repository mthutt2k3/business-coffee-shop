package com.business.coffeshop.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    private static final String UPLOAD_DIR = "src/main/resources/static/cust/images/"; // Thư mục lưu ảnh
    private static final String IMAGE_URL_PREFIX = "/cust/images/"; // Đường dẫn lưu vào DB

    public static String saveFile(MultipartFile file, String productName) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        try {
            // Tạo thư mục nếu chưa tồn tại
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Kiểm tra tên file hợp lệ
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || originalFilename.isBlank()) {
                throw new RuntimeException("Invalid file name");
            }

            // Tạo tên file chuẩn từ productName
            String slug = productName.toLowerCase().replaceAll("[^a-z0-9]", "-");
            String fileExtension = getFileExtension(originalFilename);
            String fileName = "product-" + slug + "." + fileExtension;

            // Đường dẫn file
            Path filePath = uploadPath.resolve(fileName);

            // Ghi đè file nếu đã tồn tại
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Trả về đường dẫn lưu vào DB
            return IMAGE_URL_PREFIX + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file: " + e.getMessage());
        }
    }

    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex == -1) ? "jpg" : fileName.substring(lastDotIndex + 1);
    }
}
