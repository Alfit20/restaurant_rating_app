package com.example.app_restaurant.controller;

import com.example.app_restaurant.dto.ReviewDto;
import com.example.app_restaurant.sevice.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review/{id}")
    public String addReviewToPlace(@PathVariable Long id, ReviewDto reviewDto, @RequestParam(value = "rating") int votes,
                                   RedirectAttributes attributes, Principal principal) {
        if (reviewService.checkReview(id, principal.getName())) {
            attributes.addFlashAttribute("dto", reviewDto);
            return "redirect:/" + id;
        }
        reviewService.addNewReview(id, reviewDto, principal.getName(), votes);
        return "redirect:/" + id;
    }
}

