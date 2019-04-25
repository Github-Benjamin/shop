package cn.benjamin.shop.user.service;

import cn.benjamin.shop.user.dao.UserDao;
import cn.benjamin.shop.user.vo.User;
import cn.benjamin.shop.utils.MailUitls;
import cn.benjamin.shop.utils.PageBean;
import cn.benjamin.shop.utils.UUIDUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        user.setState(0); // 设置激活状态
        String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
        user.setCode( code ); // 设置激活码
        userDao.save(user);

        MailUitls.sendMail(user.getEmail(), code);// 发送激活邮件;

    }

    // 业务层查找激活码
    public User findByUserCode(String code) {
        return userDao.findByUserCode(code);
    }

    // 业务层激活用户，修改状态
    public void updata(User existUser) {
        userDao.updata(existUser);
    }

    // 业务层登陆方法
    public User login(User user) {
        return userDao.login(user);
    }

    // 业务层查询所有用户的方法
    public PageBean<User> findByPage(Integer page) {
        PageBean<User> pageBean = new PageBean<User>();
        // 设置当前的页数
        pageBean.setPage(page);
        // 设置每页显示记录数
        int limit = 10;
        pageBean.setLimit(limit);
        // 设置总记录
        int totalCount = userDao.findCount();
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        int totalPage = 0;
        if(totalCount % limit == 0 ){
            totalPage = totalCount / limit;
        }else {
            totalPage = totalCount / limit +1;
        }
        pageBean.setTotalPage(totalPage);
        // 设置每页显示数据集合
        int begin = (page -1)*limit;
        List<User> list = userDao.findByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
    }

    // 业务层删除用户的方法
    public void delete(User user) {
        userDao.delete(user);
    }
}
