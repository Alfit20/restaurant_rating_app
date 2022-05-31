package com.example.app_restaurant.dto;

import com.example.app_restaurant.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
    private Long id;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String name;


    @NotBlank
    @Size(min = 1, max = 128)
    private String photo;

    @NotBlank
    @Size(min = 1, max = 128)
    private String description;

    public static PlaceDto from(Place place) {
        return builder()
                .id(place.getId())
                .name(place.getName())
                .photo(place.getPhoto())
                .description(place.getDescription())
                .build();

    }
}
