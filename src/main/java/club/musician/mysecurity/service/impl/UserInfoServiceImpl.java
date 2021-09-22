package club.musician.mysecurity.service.impl;

import club.musician.mysecurity.dao.UserInfoDao;
import club.musician.mysecurity.entity.UserInfo;
import club.musician.mysecurity.service.UserInfoService;

import javax.annotation.Resource;

public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDao userInfoDao;


    @Override
    public UserInfo findUserInfo(String username) {
        return userInfoDao.findByUsername(username);
    }
}
