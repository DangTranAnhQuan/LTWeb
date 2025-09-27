package vn.iotstar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories") 
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id") 
    private Long categoryId;

    @Column(name = "cate_name", columnDefinition = "nvarchar(255)") 
    private String categoryName;

    private String icon;

    @Column(name = "user_id")
    private Integer userId;

    private String images;

    public Category() {
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}