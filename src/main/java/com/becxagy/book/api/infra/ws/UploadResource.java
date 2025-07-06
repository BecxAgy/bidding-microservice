package com.becxagy.book.api.infra.ws;

import com.becxagy.book.api.core.command.UploadCommand;
import com.becxagy.book.api.core.usecase.UploadBookUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/upload")
public class UploadResource {

    @PostMapping
    public ResponseEntity<String> uploadBook(@ModelAttribute("file") UploadCommand uploadCommand){

        return ResponseEntity.ok(uploadCommand.file().getOriginalFilename());
    }

}
