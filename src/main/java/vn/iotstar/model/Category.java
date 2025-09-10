package vn.iotstar.model;

import java.io.Serializable;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer cateid;    
    private String  catename;  
    private String  icon;      
    private Integer userId;     

    public Category() {}

    public Category(Integer cateid, String catename, String icon, Integer userId) {
        this.cateid = cateid;
        this.catename = catename;
        this.icon = icon;
        this.userId = userId;
    }

    public Integer getCateid() {
        return cateid;
    }
    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    public String getCatename() {
        return catename;
    }
    public void setCatename(String catename) {
        this.catename = catename;
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

    @Override
    public String toString() {
        return "Category [cateid=" + cateid +
               ", catename=" + catename +
               ", icon=" + icon +
               ", userId=" + userId + "]";
    }
}
