package com.jaunty.dao;

import com.jaunty.domain.entity.SUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SUser record);

    int insertSelective(SUser record);

    SUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SUser record);

    int updateByPrimaryKey(SUser record);
}