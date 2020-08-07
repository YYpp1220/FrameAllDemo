package com.djh.securitytest.service.impl;

import com.djh.securitytest.dao.SysUserRoleDao;
import com.djh.securitytest.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2020-07-31 14:01:45
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl {
    @Autowired(required = false)
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    public SysUserRole queryById(Integer userId) {
        return this.sysUserRoleDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public List<SysUserRole> queryAllByLimit(int offset, int limit) {
        return this.sysUserRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    public SysUserRole insert(SysUserRole sysUserRole) {
        this.sysUserRoleDao.insert(sysUserRole);
        return sysUserRole;
    }

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    public SysUserRole update(SysUserRole sysUserRole) {
        this.sysUserRoleDao.update(sysUserRole);
        return this.queryById(sysUserRole.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer userId) {
        return this.sysUserRoleDao.deleteById(userId) > 0;
    }

    public List<SysUserRole> listByUserId(Integer userId) {
        return sysUserRoleDao.listByUserId(userId);
    }
}