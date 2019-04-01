package cn.benjamin.shop.adminuser.service;

import cn.benjamin.shop.adminuser.dao.AdminUserDao;
import cn.benjamin.shop.adminuser.vo.AdminUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * 后台登陆的业务层类
 * Created by Benjamin on 2019/4/1.
 */

@Transactional // 事务管理
public class AdminUserService {
    // 注入DAO
    private AdminUserDao adminUserDao;

    public void setAdminUserDao(AdminUserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
    }

    // 业务层登陆的方法
    public AdminUser login(AdminUser adminUser) {
        return adminUserDao.login(adminUser);
    }
}
