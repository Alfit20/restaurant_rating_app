package com.example.app_restaurant.controller;

import com.example.app_restaurant.dto.PlaceDto;
import com.example.app_restaurant.exception.ResourceNotFoundException;
import com.example.app_restaurant.sevice.CustomerService;
import com.example.app_restaurant.sevice.PlaceService;
import com.example.app_restaurant.sevice.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;
    private final ReviewService reviewService;
    private final CustomerService customerService;

    @GetMapping
    public String getAllPlaces(Model model, Principal principal) {
        model.addAttribute("places", placeService.getAllPlaces());
        if(principal != null)
            model.addAttribute("email", customerService.loadUserByUsername(principal.getName()));
        return "index";
    }

    @GetMapping("/add")
    public String newPlace() {
        return "addNewPlace";
    }

    @PostMapping("/add")
    public String addNewPlace(@RequestParam("file") MultipartFile multipartFile, PlaceDto placeDto, Principal principal) {
        placeService.addNewPlace(multipartFile, placeDto, principal);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "searchBy", required = false) String type,
                         @RequestParam(value = "search", required = false) String value,
                         Model model) {
        if (value == null || placeService.search(type, value).isEmpty()) {
            throw new ResourceNotFoundException(value);
        }
        model.addAttribute("places", placeService.search(type, value));
        return "place_by_search";
    }

    @GetMapping("{id}")
    public String placeById(@PathVariable Long id, Model model) {
        model.addAttribute("place", placeService.getProductById(id));
        model.addAttribute("icons", placeService.getAllPhotosByPlace(id));
        model.addAttribute("reviews", placeService.getAllPlaceReviews(id));
        model.addAttribute("count", placeService.countReviews(id));
        if (placeService.countReviews(id) == 0) {
            model.addAttribute("average", "-");
        }
        if (placeService.countReviews(id) != 0) {
            model.addAttribute("average", reviewService.calculateTheArithmeticMean(id));
        }
        return "place";
    }


}

