package club.musician.mysecurity.provider;

import club.musician.mysecurity.dao.UserInfoDao;
import club.musician.mysecurity.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component(value = "myUserDetailService")
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private UserInfoDao userInfoDao;


    /**
     * 1、UserDetails
     *  Collection<? extends GrantedAuthority> getAuthorities(); //权限集合
     *  boolean isAccountNonExpired();  //账号是否过期
     *  boolean isAccountNonLocked();   //账号是否锁定
     *  boolean isCredentialsNonExpired(); //证书是否过期
     *  boolean isEnabled(); //账号是否可用
     *  只有这些Boolean类型的方法返回都为真的时候，才没问题
     *
     * 2、User implements UserDetails
     *  可以自定义类，实现UserDetails接口，作为你自己系统中的用户类，这个类可以交给Spring Security作为认证使用的类
     *
     * 3、UserDetailsService接口：
     *  主要作用：获取用户信息，得到的是UserDetails对象，一般项目中都需要自定义这个接口，从数据库中获取数据。
     *  只需要实现一个方法 loadUserByUsername(String username)；根据用户名称，获取用户信息(用户名，密码，角色结合，是否可用，是否锁定的用户信息)
     *
     * 4、interface UserDetailsManager extends UserDetailsService
     *      InMemoryUserDetailsManager implements UserDetailsManager
     *      数据不是持久的，使用起来比较方便
     *
     * 5、interface JdbcUserDetailsManager extends UserDetailsService
     *     用户数据信息存储在数据库中，磁层使用jdbcTemplate操作数据库，可以在JdbcUserDetailsManager中的方法完成用户管理，
     *     快速搭建用户认证模块
     *     createUser:创建用户
     *     updateUser:更新用户
     *     deleteUser:删除啊用户
     *     userExists:判断用户是否存在
     *    org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl的同级目录的users.ddl
     *
     *
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        if (username != null) {
            userInfo = userInfoDao.findByUsername(username);

            if (userInfo != null) {

                //返回对象是一个UserDetails的这个对象，这个UserDetails的构造函数，需要一个User
                //这个User需要三个参数，用户名，密码，Authority集合
                List<GrantedAuthority> authorities = new ArrayList<>();

                //必须以"ROLE_"开头
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + userInfo.getRole());
                authorities.add(grantedAuthority);

                User user = new User(userInfo.getUsername(), userInfo.getPassword(), authorities);
                System.out.println("username:" + userInfo.getUsername() + "   password " + userInfo.getPassword());
                return user;
            }
        }
        return null;
    }



}
