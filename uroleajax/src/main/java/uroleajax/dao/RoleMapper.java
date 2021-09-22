package uroleajax.dao;

import org.springframework.stereotype.Repository;
import uroleajax.entity.SysRole;

import java.util.List;

@Repository
public interface RoleMapper {

    List<SysRole> selectRoleByUser(Integer userId);

}
