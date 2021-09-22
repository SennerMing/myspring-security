package club.musician.memory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ApplicationConfig {

    //创建一个密码加密器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //创建UserDetailsService的实现类对象
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder passwordEncoder = passwordEncoder();

        //创建内存的UserDetailsService的实现类对象
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        //创建用户admin
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN", "USER").build());
        //创建用户zhangsan
        manager.createUser(User.withUsername("zhangsan")
                .password(passwordEncoder.encode("123"))
                .roles("USER").build());

        return manager;
    }

}
