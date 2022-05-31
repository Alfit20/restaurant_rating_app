package com.example.app_restaurant.controller;

import com.example.app_restaurant.sevice.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class GalleryController {
    private final GalleryService galleryService;

    @PostMapping("/add/{id}")
    public String addNewImage(@PathVariable Long id, @RequestParam("file") MultipartFile multipartFile) {
        galleryService.addNewImage(id, multipartFile);
        return "redirect:/" + id;
    }
}
