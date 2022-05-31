package com.example.app_restaurant.dto;

import com.example.app_restaurant.entity.Customer;
import com.example.app_restaurant.entity.Place;
import com.example.app_restaurant.entity.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long id;

    @NotBlank
    @Size(min = 1, max = 128, message = "Text should be between 1 and 128 characters")
    private String text;

    @NotNull
    @JsonProperty("date_added")
    private LocalDateTime dateAdded;

    @NotNull
    @JsonProperty("author_id")
    private Customer author;

    @NotNull
    @JsonProperty("place_id")
    private Place place;

    public static ReviewDto from(Review review) {
        return builder()
                .id(review.getId())
                .text(review.getText())
                .dateAdded(review.getDateAdded())
                .author(review.getAuthor())
                .place(review.getPlace())
                .build();
    }

}
