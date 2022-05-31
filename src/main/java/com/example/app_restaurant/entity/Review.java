package com.example.app_restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 128, message = "Text should be between 1 and 128 characters")
    private String text;

    @NotNull
    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Customer author;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @NotNull
    private Integer votes;


}
