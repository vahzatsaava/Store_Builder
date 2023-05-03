package com.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String productName;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "url")
    private String url;
    @Column(name = "category")
    private String category;
    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<KeepRoom> keepRooms;
}
