package org.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.test.entity.Permission;

import java.util.List;

public interface PermissionDao extends JpaRepository<Permission,Integer> {
    @Query("select p.permissionName from User u,UserRole ur,Role r,RolePermission rp,Permission p " +
            "where u.id=ur.userId " +
            "and ur.roleId=r.id " +
            "and r.id=rp.roleId " +
            "and rp.permissionId=p.id " +
            "and u.username=?1"+
            " group by p.permissionName")
    //这里group by前面加空格
    List<String> findPermissionByUsername(String username);
}
