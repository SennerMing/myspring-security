package club.musician.userroleajax.dao;

import club.musician.userroleajax.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    List<SysRole> selectRoleByUser(Integer userId);

}
