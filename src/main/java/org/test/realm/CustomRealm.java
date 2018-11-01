package org.test.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.test.dao.PermissionDao;
import org.test.dao.UserDao;
import org.test.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 提供给shiro和数据库进行交互的一个中间层
 */
public class CustomRealm extends AuthorizingRealm {
    private static final String slat = "124123daklfqe213nogilq2eng@!#$!@!#$!qlnfmlqk112";
    @Autowired
    private UserDao userDao;
   @Autowired
   private PermissionDao permissionDao;


    /**
     * 授权
     * 用于判断用户是不是某角色具有某权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        // 从数据库或者缓存中获得角色数据
        Set<String> roles = getRolesByUserName(username);
        Set<String> permissions = getPermissionsByUserName(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);

        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionsByUserName(String username) {
        List<String> list = permissionDao.findPermissionByUsername(username);
        Set<String> sets = new HashSet<String>(list);
        return sets;
    }

    private Set<String> getRolesByUserName(String username) {
        List<String> list= userDao.findTest(username);
        Set<String> sets = new HashSet<String>(list);
        return sets;
    }

    /**
     * 认证
     * 登录验证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1.从主体传过来的认证信息中，获得用户名
        String username = (String) token.getPrincipal();

        // 2.通过用户名到数据库中获取凭证
        String password = getPasswordByUsername(username);
        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,
                password, "customRealm");
        //加盐
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username + slat));
        return simpleAuthenticationInfo;
    }

    private String getPasswordByUsername(String username) {
        List<User> user = userDao.findByUsername(username);
        if (user.size() > 0) {
            System.out.println("授权下的"+user.get(0));
            return user.get(0).getPassword();
        }
        return null;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123", "Mike" + slat, 1);
        System.out.println(md5Hash);
    }
}