package org.test.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleName;

    @ManyToMany
   //自动生成 @JoinTable(name = "role_permission",joinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "permission_id",referencedColumnName = "id"))
    private List<Permission> permission;

    public List<Permission> getPermission() {
        return permission;
    }

    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }

    @OneToMany(mappedBy="role",fetch=FetchType.EAGER)
    private List<UserRole> userRole;

    public List<UserRole> getUserRole() {
        return userRole;
    }
    @JsonBackReference
    public void setUserRole(List<UserRole> userRole) {
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}