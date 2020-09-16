package com.djh.securitytest.entity;

import java.io.Serializable;

/**
 * (SysUser)实体类
 * 自定义UserDetails
 * @author makejava
 * @since 2020-07-31 13:57:23
 */
public class SysUser implements Serializable {
    private static final long serialVersionUID = -83648274352595970L;
    
    private Integer id;
    
    private String name;
    
    private String password;

    //isAccountNonExpired方法返回boolean类型，用于判断账户是否未过期，未过期返回true反之返回false
    private boolean accountNonExpired = true;

    //isAccountNonLocked方法用于判断账户是否未锁定；
    private boolean accountNonLocked= true;

    //isCredentialsNonExpired用于判断用户凭证是否没过期，即密码是否未过期
    private boolean credentialsNonExpired= true;

    //isEnabled方法用于判断用户是否可用。
    private boolean enabled= true;

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}