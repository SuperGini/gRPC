package com.gini.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String model;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Manufacturer manufacturer;

    @JoinColumn(name = "var_Version_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Version> carVersions;


}
