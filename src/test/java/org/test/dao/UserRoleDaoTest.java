package org.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleDaoTest {
    @Autowired
    private UserRoleDao userRoleDao;
    @Test
    public void print1(){

        System.out.println(userRoleDao.findById(198).get());

    }

}