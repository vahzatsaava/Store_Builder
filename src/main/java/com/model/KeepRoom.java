package com.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "keeproom")
@Data
public class KeepRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

}
