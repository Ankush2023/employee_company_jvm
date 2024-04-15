package com.epms.company.utility;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileStrorageOperation {

    private static final String UPLOAD_DIR = "C:\\Users\\OMS\\Downloads\\company\\src\\main\\resources\\templates\\img";

    public String uploadFile(MultipartFile file) {
        if (file == null) {
            return null;
        } else {
            try {
                byte[] bytes = file.getBytes();
                File uploadedFile = new File(UPLOAD_DIR + File.separator + file.getOriginalFilename());
                uploadedFile.createNewFile();
                Files.write(uploadedFile.toPath(), bytes);
                return uploadedFile.getName();
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed to upload file";
            }
        }
    }

    public String getFilePath(String fileName) {
        if (fileName != null) {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
            File file = filePath.toFile();
            if (file.exists() && file.isFile()) {
                return filePath.toString();
            } else {
                return "File not found";
            }
        } else {
            return null;
        }
    }
}
