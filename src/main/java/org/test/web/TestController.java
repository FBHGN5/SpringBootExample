package org.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.test.dao.UserDao;
import org.test.entity.User;

@Controller

public class TestController {
    @Autowired
    private UserDao userDao;



    @GetMapping("/test")
    public String index1() {
        return "cms/test";
    }

    /**
     * 分页实现
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/page")
    public String page(@RequestParam(value = "page", defaultValue = "1",required = true) Integer page,Model model) {
       System.out.println("当前页："+page);
       //分页关键代码,将查询的结果集转为page<T>对象类型 Pageable设置当前页，每页大小，还有设置查询结果的顺序
        Pageable pageable = new PageRequest(page-1, 5);
        Page<User> p = userDao.findAll(pageable);
        int arr[]=new int[p.getTotalPages()];
        for(int i=0;i<arr.length;i++){
            arr[i]=i+1;
        }
        model.addAttribute("page",p);
        model.addAttribute("arr",arr);
        return "page";
    }
}
