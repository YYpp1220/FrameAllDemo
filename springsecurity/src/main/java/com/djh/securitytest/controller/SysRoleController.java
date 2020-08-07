package com.djh.securitytest.controller;

import com.djh.securitytest.entity.SysRole;
import com.djh.securitytest.service.impl.SysRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysRole)表控制层
 *
 * @author makejava
 * @since 2020-07-31 13:51:37
 */
@RestController
@RequestMapping("sysRole")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Autowired
    private SysRoleServiceImpl sysRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysRole selectOne(Integer id) {
        return this.sysRoleService.queryById(id);
    }

}