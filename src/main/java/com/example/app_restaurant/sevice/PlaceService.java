package com.example.app_restaurant.sevice;

import com.example.app_restaurant.dto.GalleryDto;
import com.example.app_restaurant.dto.PlaceDto;
import com.example.app_restaurant.dto.ReviewDto;
import com.example.app_restaurant.entity.Place;
import com.example.app_restaurant.exception.ResourceNotFoundException;
import com.example.app_restaurant.helpers.FileUploadUtil;
import com.example.app_restaurant.repository.GalleryRepository;
import com.example.app_restaurant.repository.PlaceRepository;
import com.example.app_restaurant.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final GalleryRepository galleryRepository;
    private final ReviewRepository reviewRepository;

    public List<PlaceDto> getAllPlaces() {
        return placeRepository.findAll().stream().map(PlaceDto::from).collect(Collectors.toList());
    }

    @SneakyThrows
    public void addNewPlace(MultipartFile multipartFile, PlaceDto placeDto, Principal principal) {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String path = "images";
        placeRepository.save(Place.builder()
                .name(placeDto.getName())
                .description(placeDto.getDescription())
                .photo(fileName)
                .build());
        FileUploadUtil.saveFile(fileName, path, multipartFile);
    }

    public List<PlaceDto> search(String type, String value) {
        if (type.equals("name")) {
            return placeRepository.searchByName(value).stream().map(PlaceDto::from).collect(Collectors.toList());
        }
        if (type.equals("description")) {
            return placeRepository.searchByDescription(value).stream().map(PlaceDto::from).collect(Collectors.toList());
        }
        return null;
    }


    public PlaceDto getProductById(Long id) {
        return PlaceDto.from(placeRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public List<GalleryDto> getAllPhotosByPlace(Long id) {
        return galleryRepository.findByPlaceId(id).stream().map(GalleryDto::from).collect(Collectors.toList());
    }

    public List<ReviewDto> getAllPlaceReviews(Long id) {
        return reviewRepository.findByPlaceId(id).stream().map(ReviewDto::from).collect(Collectors.toList());
    }

    public int countReviews(Long id) {
        return getAllPlaceReviews(id).size();
    }
}
