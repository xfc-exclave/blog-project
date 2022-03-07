package cc.chinmoku.springcloudprovideruser.service;

import cc.chinmoku.springcloudcommon.entity.User;

public interface UserService {

    User findById(String id);
}
