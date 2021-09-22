package club.musician.userroleajax.dao;

import club.musician.userroleajax.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {
    int insertSysUser(SysUser sysUser);

    SysUser selectSysUser(String username);
}
