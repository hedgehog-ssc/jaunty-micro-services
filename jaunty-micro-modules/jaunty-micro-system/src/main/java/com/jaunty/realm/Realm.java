package com.jaunty.realm;

import com.jaunty.domain.entity.SUser;
import com.jaunty.service.SUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class Realm extends AuthorizingRealm {

    @Autowired(required = false)
    private SUserService userService;

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123465";
        Object salt = ByteSource.Util.bytes("admin");
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println("加密后：" + result);
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1、AuthenticationToken 转换为 sernamePasswordToken；
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //2、从UsernamePasswordToken中获取 username；
        String username = token.getUsername();

        //3、从数据库中获取 username 和 password；
        SUser currentUser = userService.getByUsername(username);

        //4、用户不存在，抛出 UnknownAccountException；
        if("unknown".equals(username)){
            throw new UnknownAccountException("用户不存在！");
        }

        //5、根据用户信息的情况，判断是否需要抛出其他异常；
        if("locked".equals(username)){
            throw new LockedAccountException("用户被锁定！");
        }

        //6、根据用户情况，构建 AuthenticationInfo（接口） 并返回，一般使用 SimpleAuthenticationInfo（实现类）；
        //①principal：认证的实体信息，可以是username，也可以是用户实体类对象；
        Object principal = username;

        //②credentials：密码；
        Object credentials = currentUser.getPassword();

        //③realmName：当前realm对象的name，直接调用父类的getName方法即可；
        String realmName = getName();

        //加盐；
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
        info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
        return info;
    }


    /**
     * 授权：被shiro回调的方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1、从PrincipalCollection中获取登录用户的信息；
        Object principal = principalCollection.getPrimaryPrincipal();

        //2、创建 SimpleAuthorizionInfo；
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //3、利用登录的用户信息确定角色和权限（一般需要查询数据库），并设置到 SimpleAuthorizionInfo 中，然后返回；
        Set<String> roles = new HashSet<>();
        Set<String> menus = new HashSet<>();
        if("admin".equals(principal)){
            roles.add("admin");
            info.addStringPermission("*:*:*");
        }else{
            info.addRoles(roles);
            info.addStringPermissions(menus);
        }

        return info;
    }
}
