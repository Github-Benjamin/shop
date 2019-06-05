package cn.benjamin.shop.adminuser.dao;

import cn.benjamin.shop.adminuser.vo.AdminUser;
import cn.benjamin.shop.utils.PageHibernateCallback;
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

    // DAO层统计所有后台用户个数的方法
    public int findCount() {
        String hql = "select count(*) from AdminUser";
        List<Long> list= this.getHibernateTemplate().find(hql);
        if(list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    // DAO层分页查询所有后台用户的方法
    public List<AdminUser> findByPage(int begin, int limit) {
        String hql = "from AdminUser order by uid desc";
        List<AdminUser> list = this.getHibernateTemplate().execute(new PageHibernateCallback<AdminUser>(hql,null,begin,limit));
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    // DAO层查询用户的方法
    public AdminUser findByUid(Integer uid) {
        return this.getHibernateTemplate().get(AdminUser.class,uid);
    }

    // DAO层保存用户的方法
    public void save(AdminUser adminUser) {
        this.getHibernateTemplate().save(adminUser);
    }

    // DAO层修改用户的方法
    public void update(AdminUser adminUser) {
        this.getHibernateTemplate().update(adminUser);
    }

    // DAO层删除用户的方法
    public void delete(AdminUser adminUser) {
        this.getHibernateTemplate().delete(adminUser);
    }

}
