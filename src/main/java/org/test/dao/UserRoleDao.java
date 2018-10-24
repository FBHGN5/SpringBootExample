package org.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.entity.UserRole;

public interface UserRoleDao extends JpaRepository<UserRole,Integer> {
}
