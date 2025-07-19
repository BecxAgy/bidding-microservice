package com.becxagy.book.api.shared.utils.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    public static File convertMultipartFileToFile(final MultipartFile multipartFile) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            
            
            if (originalFilename == null || originalFilename.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do arquivo não pode ser vazio");
            }
            
            
            String extension = getFileExtension(originalFilename).toLowerCase();
            if (!extension.equals(".pdf") && !extension.equals(".docx")) {
                throw new IllegalArgumentException("Apenas arquivos PDF e DOCX são permitidos");
            }
            
            
            File file = File.createTempFile("upload_", extension);
            
            // Copiar conteúdo
            try (InputStream inputStream = multipartFile.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(file)) {
                
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            
            return file;
            
        } catch (IOException e) {
            throw new RuntimeException("Erro ao converter MultipartFile para File: " + e.getMessage(), e);
        }
    }
    
    private static String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return "";
        }
        return filename.substring(lastDotIndex);
    }
}