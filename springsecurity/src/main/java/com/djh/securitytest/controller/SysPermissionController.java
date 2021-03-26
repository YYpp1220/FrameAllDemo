package com.djh.securitytest.controller;

import com.djh.securitytest.entity.SysPermission;
import com.djh.securitytest.service.impl.SysPermissionServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SysPermission)表控制层
 *
 * @author makejava
 * @since 2020-09-18 15:57:46
 */
@RestController
@RequestMapping("sysPermission")
public class SysPermissionController {
    /**
     * 服务对象
     */
    @Resource
    private SysPermissionServiceImpl sysPermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysPermission selectOne(Integer id) {
        return this.sysPermissionService.queryById(id);
    }

}