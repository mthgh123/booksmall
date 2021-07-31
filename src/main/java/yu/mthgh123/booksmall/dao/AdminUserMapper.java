package yu.mthgh123.booksmall.dao;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import yu.mthgh123.booksmall.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    int insert(AdminUser record);

    /**
     * 修改密码
     * @param adminUserId
     * @param newPassword
     * @return
     */
    int updatePassword(@Param("adminUserId") Integer adminUserId, @Param("newPassword") String newPassword);

    /**
     * 登陆方法
     *
     * @param userName
     * @param password
     * @return
     */
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    AdminUser selectByPrimaryKey(Integer adminUserId);
}