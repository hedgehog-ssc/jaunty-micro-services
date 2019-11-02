package com.jaunty.service;

import com.jaunty.dao.SUserMapper;
import com.jaunty.domain.entity.SUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SUserService {
    @Autowired(required = false)
    private SUserMapper userMapper;

    /**
     * 根据ID获取用户；
     * @param id
     * @return
     */
    public SUser getById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户账户获取用户实体--用户登录shiro校验；
     * @param username
     * @return
     */
    public SUser getByUsername(String username) {
        SUser sUser = new SUser();
        return sUser;
    }
}
