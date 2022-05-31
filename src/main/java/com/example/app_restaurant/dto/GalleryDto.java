package com.example.app_restaurant.dto;

import com.example.app_restaurant.entity.Gallery;
import com.example.app_restaurant.entity.Place;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GalleryDto {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 128)
    private String photo;

    @ManyToOne
    @JsonProperty("place_id")
    private Place place;

    public static GalleryDto from(Gallery gallery) {
        return builder()
                .id(gallery.getId())
                .photo(gallery.getPhoto())
                .place(gallery.getPlace())
                .build();

    }
}
