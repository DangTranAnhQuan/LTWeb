package vn.iotstar.entity;

import jakarta.persistence.*;

@Entity @Table(name="Video")
public class Video {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, length=200)
    private String title;

    @Column(nullable=false, length=100)
    private String href; 

    @Column(length=255)
    private String poster;

    @Column(length=1000)
    private String description;

    private Boolean active = Boolean.TRUE;
    private Long views = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cateid")
    private Category category;

    public Integer getId(){return id;}
    public void setId(Integer id){this.id=id;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}
    public String getHref(){return href;}
    public void setHref(String href){this.href=href;}
    public String getPoster(){return poster;}
    public void setPoster(String poster){this.poster=poster;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public Boolean getActive(){return active;}
    public void setActive(Boolean active){this.active=active;}
    public Long getViews(){return views;}
    public void setViews(Long views){this.views=views;}
    public Category getCategory(){return category;}
    public void setCategory(Category category){this.category=category;}
}

