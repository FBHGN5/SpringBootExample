package org.test.web;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.dao.UserDao;
import org.test.dao.UserRoleDao;
import org.test.entity.User;
import org.test.entity.UserRole;

import java.util.HashMap;
import java.util.Map;

@Controller

public class TestController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
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
    @GetMapping("/user")
    @ResponseBody
    public User u(){
        User u=new User();
        u.setUsername("中午！");
        return u;
    }
    @GetMapping("/test2")
    public String test(){
        return "one";
    }
    /**
     * 一对多
     * 解决@OneToMany注解序列化json格式栈溢出
     */
    @GetMapping("/oneTomany")
    @ResponseBody
    public JSONObject test2(){
        User user=userDao.findById(1).get();
        //将list集合分解，将user的成员变量单独一个个的传入
        JSONObject jsonobject = new JSONObject();
        //传入user属性,一个个写
        //JSONObject jsonobject = JSONObject.fromObject(user);这种写法也会序列化list集合@Jsonback注解不起作用
        jsonobject.put("username",user.getUsername());
        jsonobject.put("email",user.getEmail());
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<user.getUserRole().size();i++){
            JSONObject jo= new JSONObject();
            //传入userRole属性
            jo.put("id",user.getUserRole().get(i).getId());
            jo.put("userId",user.getUserRole().get(i).getUserId());
            //生成json数组
            jsonArray.add(i,jo);
        }
        //将json数组传入json对象
        jsonobject.element("userRole",jsonArray);
        return jsonobject;
    }
    /**
     * 最简单写法
     * 一对多
     * 解决@OneToMany注解使用Map
     */
    @GetMapping("/oneTomany2")
    @ResponseBody
    public Map<String,Object> test3(){
        User user=userDao.findById(1).get();
        //将list集合分解，将user的成员变量单独一个个的传入
        Map<String,Object> map=new HashMap<>();
        map.put("user",user);
        map.put("userRole",user.getUserRole());
        //User不会返回一对多的List，需要手动添加
        return map;
    }
    /**
     * 最简单写法
     * 多对一不会报错
     */
    @GetMapping("/oneTomany3")
    @ResponseBody
    public UserRole test4(){

        return userRoleDao.findById(198).get();
    }
}
