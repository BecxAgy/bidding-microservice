package com.becxagy.book.api.adapters.out.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StoragePort {
    String upload( final MultipartFile file);
    void delete(String filename);

}
