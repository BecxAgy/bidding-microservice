package com.becxagy.book.api.application.command;

import org.springframework.web.multipart.MultipartFile;

import com.becxagy.book.api.shared.utils.validation.ValidFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBiddingCommand (@ValidFile @NotNull(message = "File is required") MultipartFile file, @NotBlank String name, @NotBlank String description){}
