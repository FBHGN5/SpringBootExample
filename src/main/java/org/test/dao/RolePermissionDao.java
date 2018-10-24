package org.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.entity.RolePermission;

public interface RolePermissionDao extends JpaRepository<RolePermission,Integer> {
}
