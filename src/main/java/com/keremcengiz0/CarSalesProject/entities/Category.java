package com.keremcengiz0.CarSalesProject.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

}
