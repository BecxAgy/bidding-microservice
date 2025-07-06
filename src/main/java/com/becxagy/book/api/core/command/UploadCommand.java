package com.becxagy.book.api.core.command;

import org.springframework.web.multipart.MultipartFile;

public record UploadCommand (MultipartFile file){}
