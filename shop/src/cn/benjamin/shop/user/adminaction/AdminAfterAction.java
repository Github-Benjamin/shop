package cn.benjamin.shop.user.adminaction;

import cn.benjamin.shop.adminuser.service.AdminUserService;
import cn.benjamin.shop.adminuser.vo.AdminUser;
import cn.benjamin.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created by Benjamin on 2019/4/25.
 */

public class AdminAfterAction extends ActionSupport implements ModelDriven<AdminUser> {
    // 模型驱动使用的对象
    private AdminUser adminUser = new AdminUser();
    public AdminUser getModel() {
        return adminUser;
    }

    // 注入AdminUserService
    private AdminUserService adminUserService;
    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    // 接收page
    private Integer page;
    public void setPage(Integer page) {
        this.page = page;
    }

    // 查询所有后台用户的方法
    public String findAll(){
        PageBean<AdminUser> pageBean = adminUserService.findByPage(page);
        // 将PageBean的数据保存到值栈中
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAll";
    }


}
