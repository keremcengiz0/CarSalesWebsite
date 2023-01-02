package com.keremcengiz0.CarSalesProject.entities;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Table(name = "advert")
@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "text")
    private String description;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}
