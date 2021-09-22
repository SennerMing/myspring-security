package club.musician.mysecurity.init;

import club.musician.mysecurity.dao.UserInfoDao;
import club.musician.mysecurity.entity.UserInfo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

//@Component
public class JdbcInit {

    @Resource
    private UserInfoDao userInfoDao;

//    @PostConstruct
    public void init() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserInfo u = new UserInfo();
        u.setUsername("zhangsan");
        u.setPassword(passwordEncoder.encode("123456"));
        u.setRole("normal");
        userInfoDao.save(u);

        u = new UserInfo();
        u.setUsername("admin");
        u.setPassword(passwordEncoder.encode("admin"));
        u.setRole("admin");
        userInfoDao.save(u);

    }

}
