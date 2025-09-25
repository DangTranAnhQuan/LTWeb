package com.example.demo.entity;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")                 
    private Integer id;

    @Column(name = "cate_name", columnDefinition = "NVARCHAR(255)")
    private String categoryName;              

    private String icon;                       
    
    @Column(name = "user_id")
    private Integer userId;                 
    
    private String images;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products;
}