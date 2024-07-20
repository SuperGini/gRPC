package com.gini.repository;

import com.gini.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, String> {

//
//    @Query("""
//
//                SELECT c FROM Car c
//                 JOIN FETCH c.manufacturer AS m ON c.manufacturer= m
//                  JOIN FETCH c.carVersions AS v ON c.carVersions = v
//                   WHERE c.id = :id
//            """)
//    Optional<Car> findCarById(String id);


}
