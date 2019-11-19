package com.jaunty.domain.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SRole {
    private Long id;

    private String roleCode;

    private String roleName;

    private String icon;

    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;

    private Byte state;

    private String remark;

}