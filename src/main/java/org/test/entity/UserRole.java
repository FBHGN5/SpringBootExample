package org.test.entity;

import javax.persistence.*;

@Entity
@Table(name="user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="user_id", insertable = false, updatable = false)
    // insertable = false, updatable = false解决多对1生成字段冲突
    private Integer userId;
    @Column(name="role_id", insertable = false, updatable = false)
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 多对一
     */
    @ManyToOne()
    @JoinColumn(name="user_id")//可以自动生成
    private User user;

    /**
     * 多对一
     */
    @ManyToOne()
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}