package com.jaunty.domain.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SUser {
    private Long id;

    private Byte type;

    private String username;

    private String password;

    private String realname;

    private String icon;

    private String telephone;

    private String email;

    private String description;

    private Byte dflag;

    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;

}