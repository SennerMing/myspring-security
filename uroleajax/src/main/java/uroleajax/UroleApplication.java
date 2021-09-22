package uroleajax;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uroleajax.entity.SysUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@MapperScan("uroleajax.dao")
public class UroleApplication {

//    @Resource
//    SysUserMapper sysUserMapper;


    public static void main(String[] args) {
        SpringApplication.run(UroleApplication.class, args);
    }

//    @PostConstruct
    public void jdbcInit() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        //参数，角色名称，需要以"ROLE_"开头，后面加上自定义的角色名称
//        GrantedAuthority grantedAuthorities = new SimpleGrantedAuthority("ROLE_"+"ADMIN");
//        GrantedAuthority grantedAuthorities = new SimpleGrantedAuthority("ROLE_"+"USER");
        GrantedAuthority grantedAuthorities = new SimpleGrantedAuthority("ROLE_"+"READ");
        authorities.add(grantedAuthorities);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//        SysUser sysUser = new SysUser("zhangsan", passwordEncoder.encode("123"), "张三",
//                true, true, true, true, new Date(), new Date(), authorities);


        SysUser sysUser = new SysUser("lisi", passwordEncoder.encode("123"), "李四",
                true, true, true, true, new Date(), new Date(), authorities);


        authorities.clear();
        GrantedAuthority grantedAuthorities1 = new SimpleGrantedAuthority("ROLE_"+"ADMIN");
        GrantedAuthority grantedAuthorities2 = new SimpleGrantedAuthority("ROLE_"+"USER");
        authorities.add(grantedAuthorities1);
        authorities.add(grantedAuthorities2);
        SysUser sysUser2 = new SysUser("admin", passwordEncoder.encode("123"), "管理员",
                true, true, true, true, new Date(), new Date(), authorities);

//        sysUserMapper.insertSysUser(sysUser);
//        sysUserMapper.insertSysUser(sysUser2);

    }


}
