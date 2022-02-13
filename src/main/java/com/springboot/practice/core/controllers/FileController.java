package com.springboot.practice.core.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class FileController {
    @Value("${file_store_path}")
    private String UPLOAD_DIR;
    @PostMapping("upload")
    public boolean upload(@RequestParam("file")MultipartFile file) throws IOException {
        file.transferTo(Paths.get(UPLOAD_DIR+file.getOriginalFilename()));
        return true;

    }

    @GetMapping("download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable("fileName")String fileName) throws IOException {
        byte[]fileData= Files.readAllBytes(Paths.get(UPLOAD_DIR+fileName));
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
    return new ResponseEntity<byte[]>(fileData,headers, HttpStatus.OK);

    }
}
