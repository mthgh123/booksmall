package yu.mthgh123.booksmall.service.impl;

import yu.mthgh123.booksmall.dao.AdminUserMapper;
import yu.mthgh123.booksmall.entity.AdminUser;
import yu.mthgh123.booksmall.service.AdminUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        return adminUserMapper.login(userName, password);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }
}

