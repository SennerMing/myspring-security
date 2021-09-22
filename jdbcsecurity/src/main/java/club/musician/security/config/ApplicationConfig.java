package club.musician.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class ApplicationConfig {
    //1.创建PasswordEncode对象
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //3.通过spring容器注入一个数据源
    @Resource
    private DataSource dataSource;

    //2.创建UserDetailsService对象
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder passwordEncoder = passwordEncoder();

//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        //4.通过数据源才能访问数据库，才能使用JdbcTemplate
        System.out.println(dataSource);
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        if (!manager.userExists("admin")) {
            manager.createUser(User.withUsername("admin")
                    .password(passwordEncoder.encode("admin"))
                    .roles("ADMIN", "USER", "MANAGER").build());
        }

        if (!manager.userExists("zhangsan")) {
            manager.createUser(User.withUsername("zhangsan")
                    .password(passwordEncoder.encode("zhangsan"))
                    .roles("USER").build());
        }
        if (!manager.userExists("lisi")) {
            manager.createUser(User.withUsername("lisi")
                    .password(passwordEncoder.encode("lisi"))
                    .roles("USER", "NORMAL").build());
        }

        return manager;
    }

}
