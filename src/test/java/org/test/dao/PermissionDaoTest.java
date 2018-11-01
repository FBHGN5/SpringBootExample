package org.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionDaoTest {
     @Autowired
     private PermissionDao permissionDao;
    @Test
    public void findByUsername() {
        permissionDao.findPermissionByUsername("admin");
    }
}