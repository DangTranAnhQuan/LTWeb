package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", columnDefinition = "NVARCHAR(255)")
    private String title;

    private int quantity;

    @Column(name = "[desc]", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    private double price;
    
    @Column(length = 255)
    private String image;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}