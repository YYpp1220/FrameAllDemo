package com.djh.securitytest.controller;

import com.djh.securitytest.entity.SysUserRole;
import com.djh.securitytest.service.impl.SysUserRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysUserRole)表控制层
 *
 * @author makejava
 * @since 2020-07-31 14:01:46
 */
@RestController
@RequestMapping("sysUserRole")
public class SysUserRoleController {
    /**
     * 服务对象
     */
    @Autowired
    private SysUserRoleServiceImpl sysUserRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUserRole selectOne(Integer id) {
        return this.sysUserRoleService.queryById(id);
    }

}