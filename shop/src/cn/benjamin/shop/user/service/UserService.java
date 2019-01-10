package cn.benjamin.shop.user.service;

import cn.benjamin.shop.user.dao.UserDao;
import cn.benjamin.shop.user.vo.User;
import cn.benjamin.shop.utils.MailUitls;
import cn.benjamin.shop.utils.UUIDUtils;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Benjamin on 2018/12/15.
 */

// 用户模块业务层代码

@Transactional // 配置事务管理
public class UserService {

    // 注入UserDao
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    // 按用户名查询用户的方法
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    // 业务层完成用户注册代码:
    public void save(User user) {
        user.setState("0"); // 设置激活状态
        String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
        user.setCode( code ); // 设置激活码
        userDao.save(user);

        MailUitls.sendMail(user.getEmail(), code);// 发送激活邮件;

    }

    // 查找激活码
    public User findByUserCode(String code) {
        return userDao.findByUserCode(code);
    }

    // 激活用户，修改状态
    public void updata(User existUser) {
        userDao.updata(existUser);
    }

    // 登陆方法
    public User login(User user) {
        return userDao.login(user);
    }
}
