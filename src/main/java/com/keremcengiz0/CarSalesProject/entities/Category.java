package com.keremcengiz0.CarSalesProject.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categories")
    private List<Vehicle> vehicles;
}
