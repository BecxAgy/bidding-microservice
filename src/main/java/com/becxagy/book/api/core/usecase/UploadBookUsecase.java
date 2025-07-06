package com.becxagy.book.api.core.usecase;

import org.springframework.web.multipart.MultipartFile;

public interface UploadBookUsecase {
    String upload(MultipartFile file);
}
