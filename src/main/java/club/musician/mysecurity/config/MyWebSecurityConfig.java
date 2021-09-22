package club.musician.mysecurity.config;

import club.musician.mysecurity.provider.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;

/**
 * @EnableGlobalMethodSecurity:启用方法级别的认证
 *  prePostEnabled:boolean 默认是false;true表示的是可以使用@PreAuthorize注解和@PostAuthorize
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    //在方法中配置 用户和密码的信息，作为登录信息

//    @Autowired
//    @Qualifier("myUserDetailService")
    @Resource
    private MyUserDetailService myUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/helloAdmin").hasRole("admin")
//                .antMatchers("/helloNormal").hasAnyRole("normal","admin")
////                .antMatchers("/user/**").hasRole("user")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/login")
//                //授权成功后交给自定义的处理器去处理
//                .successHandler(myAuthenticationSuccessHandler())
//                .permitAll().and()
//                .csrf().disable();
//
//    }

    //自定义一个授权成功的处理器
//    @Bean
//    AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
//        return new MyAuthenticationSuccessHandler();
//    }

}
