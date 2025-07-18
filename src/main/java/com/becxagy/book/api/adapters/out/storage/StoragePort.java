package com.becxagy.book.api.adapters.out.storage;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.multipart.MultipartFile;

public interface StoragePort {
    CompletableFuture<String> upload( final MultipartFile file);
    void delete(String filename);

}
