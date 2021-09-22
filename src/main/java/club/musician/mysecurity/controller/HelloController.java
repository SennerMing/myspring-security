package club.musician.mysecurity.controller;

import club.musician.mysecurity.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@CrossOrigin
public class HelloController {

    @Resource
    private UserInfoDao userInfoDao;

    @RequestMapping("/hello")
    public String sayHello() {
        return "使用内存中的用户信息";
    }

    @RequestMapping("/helloNormal")
    @PreAuthorize(value = "hasAnyAuthority('ROLE_admin','ROLE_normal')")
    public String helloCommonUser() {
        return "====Hello 拥有normal，admin角色的用户===";
    }

    @RequestMapping("/helloAdmin")
    @PreAuthorize(value = "hasAnyAuthority('ROLE_admin')")
    public String helloAdmin() {
        return "===Hello 拥有admin角色的用户===";
    }


    @GetMapping("/test")
    public void test(String username) {
        System.out.println(userInfoDao.findByUsername(username));
    }


}
