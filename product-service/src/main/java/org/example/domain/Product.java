package org.example.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="orders")
@Access(AccessType.FIELD)
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long stock;


}

