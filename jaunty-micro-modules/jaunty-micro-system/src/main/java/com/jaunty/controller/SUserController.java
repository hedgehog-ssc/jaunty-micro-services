package com.jaunty.controller;

import com.jaunty.domain.entity.SUser;
import com.jaunty.service.SUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(description = "用户操作接口")
@RestController
@RequestMapping("/system/user")
public class SUserController {

    @Autowired(required = false)
    private SUserService userService;

    @RequestMapping(value = "getById", method= RequestMethod.GET)
    @ResponseBody
    public SUser getById(Long id){
        return userService.getById(id);
    }

    /**
     * 登录
     * @param username
     * @param password
     */
    public void login(String username, String password){
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            //账号密码封装为token对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //记住我
            token.setRememberMe(true);

            try {
                //登录
                currentUser.login(token);
            } catch (AuthenticationException e) {
                log.info("登录失败：{}", e.getMessage());
            }
        }
    }

}
