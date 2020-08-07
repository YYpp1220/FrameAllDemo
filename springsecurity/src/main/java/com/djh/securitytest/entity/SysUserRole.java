package com.djh.securitytest.entity;

import java.io.Serializable;

/**
 * (SysUserRole)实体类
 *
 * @author makejava
 * @since 2020-07-31 14:01:44
 */
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 304962750412649961L;
    
    private Integer userId;
    
    private Integer roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}