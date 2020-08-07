package com.djh.securitytest.entity;

import java.io.Serializable;

/**
 * (SysRole)实体类
 *
 * @author makejava
 * @since 2020-07-31 13:51:33
 */
public class SysRole implements Serializable {
    private static final long serialVersionUID = -78579344901895527L;
    
    private Integer id;
    
    private String name;


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

}