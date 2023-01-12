package com.keremcengiz0.CarSalesProject.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Table(name = "advert")
@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "advert_date", nullable = false, updatable = false)
    private LocalDate advertDate;

    private String title;

    @Lob
    @Column(columnDefinition = "text")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Vehicle vehicle;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "advert")
    private List<Image> images;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}
