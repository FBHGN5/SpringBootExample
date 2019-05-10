package org.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;

    @Test
    public void findAll() {
        logger.warn("错误信息");
        System.out.println(userDao.findAll().get(0));
    }

    @Test
//    @Transactional
//    @Rollback(false)
    public void findByUsername() {

       // System.out.println(userDao.findById(1).get().getUserRole().get(0).getRole());
    }

    @Test
    public void findTest() {
        System.out.println(userDao.findTest("Lili"));
    }

    @Test
    public void findByNameNot() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(2, 5, sort);
        Page<User> pages = userDao.findAllByOrderByIdDesc(pageable);
        System.out.println(pages.hasNext());
    }
}