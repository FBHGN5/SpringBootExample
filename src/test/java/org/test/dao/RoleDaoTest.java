package org.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.test.entity.Role;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleDaoTest {
    @Autowired
    private RoleDao roleDao;

    @Test
    @Transactional
    //多对多测试必须加
    public void r() {
        List<Role> role = roleDao.findAll();
//      Iterator it=role.iterator();
//      while(it.hasNext()){
//          System.out.println(it.next());
//      }
        System.out.println(roleDao.findById(1).get().getPermission());
    }
}