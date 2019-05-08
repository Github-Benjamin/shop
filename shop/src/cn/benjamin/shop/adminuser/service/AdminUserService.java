package cn.benjamin.shop.adminuser.service;

import cn.benjamin.shop.adminuser.dao.AdminUserDao;
import cn.benjamin.shop.adminuser.vo.AdminUser;
import cn.benjamin.shop.utils.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    // 业务层查询所有后台用户的方法
    public PageBean<AdminUser> findByPage(Integer page) {
        PageBean<AdminUser> pageBean = new PageBean<AdminUser>();
        // 设置当前的页数
        pageBean.setPage(page);
        // 设置每页显示记录数
        int limit = 10;
        pageBean.setLimit(limit);
        // 设置总记录
        int totalCount = adminUserDao.findCount();
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
        List<AdminUser> list = adminUserDao.findByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
    }

}
