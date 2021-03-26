package com.djh.securitytest.config;

import com.djh.securitytest.entity.SysPermission;
import com.djh.securitytest.entity.SysRole;
import com.djh.securitytest.service.impl.SysPermissionServiceImpl;
import com.djh.securitytest.service.impl.SysRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 自定义权限评估者
 * @author MyMrDiao
 * @date 2020/09/18
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysPermissionServiceImpl permissionService;

    @Autowired
    private SysRoleServiceImpl roleService;

    /**
     * 有权限
     * @param authentication     身份验证
     * @param targetDomainObject 目标域对象
     * @param permission         许可
     * @return boolean
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        User user = (User)authentication.getPrincipal();
        Collection<GrantedAuthority> collection = user.getAuthorities();
        //List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
        //String roleName = authorities.stream().map(GrantedAuthority::getAuthority).toString();
        String roleName = collection.stream().map(GrantedAuthority::getAuthority).toString();
        SysRole sysRole = new SysRole();
        sysRole.setName(roleName);
        List<SysRole> sysRoles = roleService.queryAll(sysRole);
        String roleId = sysRoles.stream().map(SysRole::getId).toString();
        SysPermission sysPermission = new SysPermission();
        sysPermission.setRoleId(Integer.valueOf(roleId));
        List<SysPermission> sysPermissions = permissionService.queryAll(sysPermission);
        for (SysPermission permissions : sysPermissions) {
            List permissionsList = permissions.getPermissions();
            if (targetDomainObject.equals(permissions.getUrl()) && permissionsList.contains(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 有权限
     * @param authentication 身份验证
     * @param targetId       目标id
     * @param targetType     目标类型
     * @param permission     许可
     * @return boolean
     */
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
