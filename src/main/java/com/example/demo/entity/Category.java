//package com.example.demo.entity;
//
//import java.io.Serializable;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.NamedQuery;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Entity
//@Table(name = "categories")
//@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
//public class Category implements Serializable {
//	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	@Column(name = "categoryName", columnDefinition = "NVARCHAR(255)")
//	private String categoryname;
//	@Column(columnDefinition = "NVARCHAR(MAX)")
//	private String images;
//}

package com.example.demo.entity;

import java.io.Serializable;
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

    @Column(name = "icon")
    private String icon;                       

    @Column(name = "user_id", nullable = true)
    private Integer userId;                 
}

