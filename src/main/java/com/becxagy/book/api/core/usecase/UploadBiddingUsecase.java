package com.becxagy.book.api.core.usecase;

import org.springframework.web.multipart.MultipartFile;

public interface UploadBiddingUsecase {
    String upload(MultipartFile file);
}
