package club.musician.mysecurity.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserInfo {
    @Id
    //这个GenerationType.AUTO,自动生成的这个hibernate_sequence表
//    @GeneratedValue(strategy = GenerationType.AUTO)
    //这个GenerationType.IDENTITY主键自增的
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //用户名称
    private String username;
    //密码
    private String password;
    //角色
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
