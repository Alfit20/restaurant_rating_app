package com.example.app_restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "places")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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



}
