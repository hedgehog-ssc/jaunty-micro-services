package com.jaunty.controller;

import com.jaunty.domain.entity.SUser;
import com.jaunty.service.SUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

}
