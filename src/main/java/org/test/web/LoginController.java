package org.test.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.test.dao.*;
import org.test.entity.RolePermission;
import org.test.entity.User;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;
    @GetMapping("/check")
    @ResponseBody
    public RolePermission check(HttpSession session){
        RolePermission u=rolePermissionDao.findById(1).get();
        System.out.println("------------"+u);
        return u;
    }
    @GetMapping("/check1")
    @ResponseBody
    public int check(){
        return 151230;
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/subLogin")
    public String login(User user, HttpSession session, Model model) {
        System.out.println(user);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
                user.getPassword());
        try {
            System.out.println("记住密码：" + user.isRememberMe());
            token.setRememberMe(user.isRememberMe());
            subject.login(token);
            user = userDao.findByUsername(user.getUsername()).get(0);
            System.out.println("user{}="+user);
            session.setAttribute("user", user);
            if (subject.hasRole("admin")) {
                model.addAttribute("ad", "有admin权限");
            }
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            model.addAttribute("ad", "用户名或密码错误!");
        }


        return "success";
    }

    @GetMapping("/logout")
    public String lo(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
//            if (LOG.isDebugEnabled()) {
//                LOG.debug("用户" + username + "退出登录");
//            }
        }
        return "login";
    }

    @RequiresRoles(value = {"admin", "manager", "user"}, logical = Logical.OR)
    @RequestMapping(value = "/testRole", method = RequestMethod.GET)
    @ResponseBody
    public String testRole(HttpSession session) {
        return "test role success";
    }
}
