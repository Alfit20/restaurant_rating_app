package com.example.app_restaurant.repository;

import com.example.app_restaurant.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlaceId(Long id);
    boolean existsByPlaceIdAndAuthorEmail(Long id, String email);
}
