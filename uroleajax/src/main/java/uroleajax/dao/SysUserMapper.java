package uroleajax.dao;

import org.springframework.stereotype.Repository;
import uroleajax.entity.SysUser;

@Repository
public interface SysUserMapper {
    int insertSysUser(SysUser sysUser);

    SysUser selectSysUser(String username);
}
