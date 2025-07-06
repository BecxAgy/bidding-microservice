package com.becxagy.book.api.infra.ws;

import com.becxagy.book.api.application.service.UploadBookService;
import com.becxagy.book.api.core.command.UploadCommand;
import com.becxagy.book.api.core.usecase.UploadBookUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/upload")
public class UploadResource {

    private final UploadBookService uploadBookService;

    @Autowired
    public UploadResource(UploadBookService uploadBookService){
        this.uploadBookService = uploadBookService;
    }
    @PostMapping
    public ResponseEntity<String> uploadBook(@ModelAttribute("file") UploadCommand uploadCommand){
        uploadBookService.upload(uploadCommand.file());
        return ResponseEntity.ok(uploadCommand.file().getOriginalFilename());
    }

}
