package vn.iotstar.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "users") 
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 120)
    private String email;

    @Column(nullable = false, unique = true, length = 80)
    private String username;

    @Column(name = "fullName", length = 120)
    private String fullName;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(name = "roleid")
    private Integer roleId;

    @Column(length = 20)
    private String phone;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    // ===== getters/setters =====
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public Integer getRoleId() { return roleId; }
    public void setRoleId(Integer roleId) { this.roleId = roleId; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
}
