package com.djh.securitytest.service.impl;

import com.djh.securitytest.dao.SysPermissionDao;
import com.djh.securitytest.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysPermission)表服务实现类
 *
 * @author makejava
 * @since 2020-09-18 15:55:48
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl {
    @Autowired(required = false)
    private SysPermissionDao sysPermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SysPermission queryById(Integer id) {
        return this.sysPermissionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public List<SysPermission> queryAllByLimit(int offset, int limit) {
        return this.sysPermissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysPermission 实例对象
     * @return 对象列表
     */
    public List<SysPermission> queryAll(SysPermission sysPermission){
        return this.sysPermissionDao.queryAll(sysPermission);
    }

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    public SysPermission insert(SysPermission sysPermission) {
        this.sysPermissionDao.insert(sysPermission);
        return sysPermission;
    }

    /**
     * 修改数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    public SysPermission update(SysPermission sysPermission) {
        this.sysPermissionDao.update(sysPermission);
        return this.queryById(sysPermission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.sysPermissionDao.deleteById(id) > 0;
    }
}