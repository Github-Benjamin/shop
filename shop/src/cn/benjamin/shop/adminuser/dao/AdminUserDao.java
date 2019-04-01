package cn.benjamin.shop.adminuser.dao;

import cn.benjamin.shop.adminuser.vo.AdminUser;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 后台登陆的DAO的类
 * Created by Benjamin on 2019/4/1.
 */

public class AdminUserDao extends HibernateDaoSupport {

    // DAO中登陆的方法
    public AdminUser login(AdminUser adminUser) {
        String hql = "from AdminUser Where username = ? and password = ?";
        List<AdminUser> list= this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }


}
