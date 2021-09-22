package club.musician.userroleajax.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/access")
public class AccessController {

    @GetMapping(path = "/user",produces = "text/html;charset=utf-8")
    public String user() {
        return "张三是这个User";
    }

    @GetMapping(path = "/read", produces = "text/html;charset=utf-8")
    public String read() {
        return "李四是这个Read";
    }


    @GetMapping(path = "/admin", produces = "text/html;charset=utf-8")
    public String admin() {
        return "管理员是这个Admin";
    }

}
