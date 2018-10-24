package org.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleDaoTest {
    @Autowired
    private UserRoleDao userRoleDao;
    @Test
    public void print1(){
        User u=userRoleDao.findAll().get(3).getUser();
        System.out.println(u);

    }

}