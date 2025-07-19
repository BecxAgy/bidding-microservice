package com.becxagy.book.api.core.command;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBiddingCommand ( @NotNull(message = "File is required") MultipartFile file, @NotBlank String name, @NotBlank String description){}
