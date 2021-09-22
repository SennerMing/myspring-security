package club.musician.mysecurity.service;

import club.musician.mysecurity.entity.UserInfo;

public interface UserInfoService {
    UserInfo findUserInfo(String username);
}
