package uroleajax.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import uroleajax.common.MyFailureHandler;
import uroleajax.common.MySuccessHandler;
import uroleajax.service.JdbcUserDetailsService;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JdbcUserDetailsService userDetailsService;


    @Resource
    private MySuccessHandler mySuccessHandler;

    @Resource
    private MyFailureHandler myFailureHandler;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("configure(HttpSecurity http)");
//        super.configure(http);

        /**
         * ajax方式，用户端发起请求，spring security接收请求验证用户的用户名和密码，把验证结果赶回给请求放(json数据)
         *  1.AuthenticationSuccessHandler，当Spring security框架验证用户信息成功之后，执行的是onAuthenticationSuccess
         *  2.AuthenticationFailureHandler，当Spring security框架验证用户信息成功之后，执行的是onAuthenticationFailure
         */
        http.httpBasic().and()
                //指定不需要授权就可以访问的地址
                .authorizeRequests().antMatchers("/index", "/mylogin.html","/login").permitAll()
                .antMatchers("/access/user/**").hasRole("USER")
                .antMatchers("/access/read/**").hasRole("READ")
                .antMatchers("/access/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated().and()
                //指定咱们自定义的登录界面
                .formLogin()
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//
//                    }
//                })
                //设置成功和失败的处理器
                .successHandler(mySuccessHandler)
                .failureHandler(myFailureHandler)
                .loginPage("/myajax.html")
                //指定成form表单中action中的那个值，原本的spring security的登录地址
                .loginProcessingUrl("/login")
                //跨域关闭，为了咱们自定义的登录、报错页面管用
                .and().csrf().disable();
    }
}
