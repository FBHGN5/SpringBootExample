package org.test.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.test.entity.User;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {

    List<User> findByUsername(String username);
//  hql语句中查询条件表名为类名，字段是类的属性
    @Query("select r.roleName from User u,UserRole ur,Role r where u.id=ur.userId " +
            "and ur.roleId=r.id " +
            "and u.username=?1")
    List<String> findTest(String username);
    //实现分页功能
    Page<User> findAllByOrderByIdDesc(Pageable pageable);
}
