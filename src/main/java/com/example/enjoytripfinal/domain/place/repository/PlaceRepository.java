package com.example.enjoytripfinal.domain.place.repository;

import com.example.enjoytripfinal.domain.place.entity.Category;
import com.example.enjoytripfinal.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place, UUID> {
    @Query(value = "SELECT p FROM Place p WHERE p.name LIKE %:name% ")
    List<Place> findAllByName(@Param("name")String name);

    @Query(value = "SELECT p FROM Place p WHERE p.category = :category")
    List<Place> findAllByCategory(@Param("category") Category category);

    @Query(value = "SELECT p FROM Place p WHERE p.roadAddress like %:roadAddress%")
    List<Place> findAllByRoadAddress(@Param("roadAddress") String roadAddress);

    @Query("SELECT p FROM Place p " +
            "WHERE (:name IS NULL OR p.name LIKE %:name%) " +
            "AND (:category IS NULL OR p.category = :category) " +
            "AND (:roadAddress IS NULL OR p.roadAddress LIKE %:roadAddress%)")
    List<Place> findAllByDetail(@Param("name") String name,
                             @Param("category") Category category,
                             @Param("roadAddress") String roadAddress);
}
