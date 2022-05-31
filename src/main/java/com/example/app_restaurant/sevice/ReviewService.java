package com.example.app_restaurant.sevice;

import com.example.app_restaurant.dto.ReviewDto;
import com.example.app_restaurant.entity.Customer;
import com.example.app_restaurant.entity.Place;
import com.example.app_restaurant.entity.Review;
import com.example.app_restaurant.exception.CustomerNotFoundException;
import com.example.app_restaurant.exception.UserAlreadyRegisteredException;
import com.example.app_restaurant.repository.CustomerRepository;
import com.example.app_restaurant.repository.PlaceRepository;
import com.example.app_restaurant.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final PlaceRepository placeRepository;
    private final PlaceService placeService;

    public boolean checkReview(Long id, String email) {
        return reviewRepository.existsByPlaceIdAndAuthorEmail(id, email);
    }

    public void addNewReview(Long id, ReviewDto reviewDto, String email, int votes) {
        Customer author = customerRepository.findByEmail(email).orElseThrow(CustomerNotFoundException::new);
        Place place = placeRepository.findById(id).get();
        if (checkReview(id, author.getEmail())) {
            throw new UserAlreadyRegisteredException();
        }
        reviewRepository.save(Review.builder()
                .text(reviewDto.getText())
                .dateAdded(LocalDateTime.now())
                .author(author)
                .place(place)
                .votes(votes)
                .build());
    }

    public double calculateTheArithmeticMean(Long id) {
        double sum = 0;
        var review = reviewRepository.findByPlaceId(id);
        for (int i = 0; i < review.size(); i++) {
            sum += review.get(i).getVotes();
        }
        return sum / placeService.countReviews(id);
    }
}
