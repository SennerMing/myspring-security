package club.musician.mysecurity.dao;

import club.musician.mysecurity.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo, Long> {
    //根据username查找用户信息
    UserInfo findByUsername(String username);

}
