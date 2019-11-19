package com.jaunty.dao;

import com.jaunty.domain.entity.SRole;

public interface SRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SRole record);

    int insertSelective(SRole record);

    SRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SRole record);

    int updateByPrimaryKey(SRole record);
}