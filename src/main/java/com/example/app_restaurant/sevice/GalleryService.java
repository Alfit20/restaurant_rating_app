package com.example.app_restaurant.sevice;

import com.example.app_restaurant.entity.Gallery;
import com.example.app_restaurant.entity.Place;
import com.example.app_restaurant.exception.ResourceNotFoundException;
import com.example.app_restaurant.helpers.FileUploadUtil;
import com.example.app_restaurant.repository.GalleryRepository;
import com.example.app_restaurant.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class GalleryService {
    private final GalleryRepository galleryRepository;
    private final PlaceRepository placeRepository;

    @SneakyThrows
    public void addNewImage(Long id, MultipartFile multipartFile) {
        Place place = placeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String path = "images/icons";
        galleryRepository.save(Gallery.builder()
                .photo(fileName)
                .place(place)
                .build());
        FileUploadUtil.saveFile(fileName, path, multipartFile);
    }
}