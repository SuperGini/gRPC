package com.gini.repository;

import com.gini.model.Car;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class CustomCarRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public Car findById(String id) {

        EntityGraph<Car> carGraph = entityManager.createEntityGraph(Car.class);
        carGraph.addAttributeNodes("manufacturer");
        carGraph.addAttributeNodes("carVersions");
        carGraph.addAttributeNodes("model");
        carGraph.addAttributeNodes("id");


        var properties = new HashMap<String, Object>();
        properties.put("javax.persistence.loadgraph", carGraph);

        return entityManager.find(Car.class, id, properties);

    }


}
