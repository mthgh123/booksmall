package yu.mthgh123.booksmall.service;

import yu.mthgh123.booksmall.entity.AdminUser;

public interface AdminUserService {

    /**
     * 获取用户信息
     *
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(String userName, String password);

    /**
     * 获取用户信息
     *
     * @param loginUserId
     * @return
     */
    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 更新密码
     * @param loginUserId
     * @return
     */
    String updatePassword(Integer loginUserId, String newPassword);
}