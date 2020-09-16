package com.djh.securitytest.service.impl;

import com.djh.securitytest.entity.SysRole;
import com.djh.securitytest.entity.SysUser;
import com.djh.securitytest.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义 UserDetailsService ，将用户信息和权限注入进来
 */
@Service("customUserDetailsServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserServiceImpl userService;

    @Autowired
    private SysRoleServiceImpl roleService;

    @Autowired
    private SysUserRoleServiceImpl userRoleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 重写 loadUserByUsername 方法，参数是用户输入的用户名。返回值是UserDetails
     * @param username 用户输入用户名
     * @return 返回UserDetails
     * @throws UsernameNotFoundException 不存在或者找不到用户抛出异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUser user = userService.selectByName(username);

        System.out.println(passwordEncoder.encode(user.getPassword()));

        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }


        // 添加权限
        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = roleService.selectById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // 返回UserDetails实现类
        return new User(user.getName(), user.getPassword(), authorities);
    }
}
