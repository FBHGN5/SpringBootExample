package org.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.entity.Role;

public interface RoleDao extends JpaRepository<Role,Integer> {
}
