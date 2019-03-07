package cn.benjamin.shop.user.dao;

import cn.benjamin.shop.user.vo.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;

/**
 * Created by Benjamin on 2018/12/15.
 */

// 用户模块持久层代码

public class UserDao extends HibernateDaoSupport {

    // 按名称查询是否有该用户
    public User findByUsername(String username) {
        String hql = "from User where username = ?";
        List<User> list = this.getHibernateTemplate().find(hql, username);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    // 注册用户存入数据库代码实现
    public void save(User user) {
        this.getHibernateTemplate().save(user);
    }

    // 根据激活码查询用户
    public User findByUserCode(String code) {
        String hql = "from User where code = ?";
        List<User> list = this.getHibernateTemplate().find(hql,code);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    // 用户激活修改用户状态
    public void updata(User existUser) {
        this.getHibernateTemplate().update(existUser);
    }

    // 用户登陆方法
    public User login(User user) {
        String hql = "from User where username = ? and password = ? and state = ?";
        List<User> list = this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),"1");
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
