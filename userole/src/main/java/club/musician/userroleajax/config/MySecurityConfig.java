package club.musician.userroleajax.config;

import club.musician.userroleajax.service.JdbcUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JdbcUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("configure(HttpSecurity http)");
//        super.configure(http);

        http.httpBasic().and()
                //指定不需要授权就可以访问的地址
                .authorizeRequests().antMatchers("/index", "/mylogin.html","/login","/error.html").permitAll()
                .antMatchers("/access/user/**").hasRole("USER")
                .antMatchers("/access/read/**").hasRole("READ")
                .antMatchers("/access/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated().and()
//                .formLogin().permitAll();
                //指定咱们自定义的登录界面
                .formLogin()
//                .usernameParameter("myname")
//                .passwordParameter("mypassword")
                .loginPage("/mylogin.html")
                //指定成form表单中action中的那个值，原本的spring security的登录地址
                .loginProcessingUrl("/login")
                .failureUrl("/error.html")  //处理登录错误的页面
                //跨域关闭，为了咱们自定义的登录、报错页面管用
                .and().csrf().disable();
    }
}
