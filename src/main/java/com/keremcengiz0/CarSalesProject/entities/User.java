package com.keremcengiz0.CarSalesProject.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    String userName;

    @Column(name = "password")
    String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Advert> adverts;

}
