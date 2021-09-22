package club.musician.userroleajax.service;

import club.musician.userroleajax.dao.RoleMapper;
import club.musician.userroleajax.dao.SysUserMapper;
import club.musician.userroleajax.entity.SysRole;
import club.musician.userroleajax.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private RoleMapper roleMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //1.根据username获取用户的数据信息SysUser
        SysUser sysUser = sysUserMapper.selectSysUser(s);
        if (sysUser != null) {

            //2.根据user账号的id获取其对应的角色信息
            List<SysRole> sysRoleList = roleMapper.selectRoleByUser(sysUser.getId());
            List<GrantedAuthority> authorities = new ArrayList<>();
            sysRoleList.stream().forEach((sysRole) -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+sysRole.getName());
                authorities.add(grantedAuthority);
            });
            sysUser.setAuthorities(authorities);
            System.out.println(sysUser);
        }

        return sysUser;
    }
}
