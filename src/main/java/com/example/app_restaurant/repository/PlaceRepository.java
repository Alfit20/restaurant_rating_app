package com.example.app_restaurant.repository;

import com.example.app_restaurant.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT p FROM Place p WHERE LOWER(p.name) LIKE %?1%")
    List<Place> searchByName(String name);

    @Query("SELECT p FROM Place p WHERE LOWER(p.description) LIKE %?1%")
    List<Place> searchByDescription(String description);
}
