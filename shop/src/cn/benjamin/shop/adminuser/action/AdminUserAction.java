package cn.benjamin.shop.adminuser.action;

import cn.benjamin.shop.adminuser.service.AdminUserService;
import cn.benjamin.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

/**
 * 后台登陆的Action
 * Created by Benjamin on 2019/4/1.
 */

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
    // 模型驱动使用的对象
    private AdminUser adminUser = new AdminUser();
    public AdminUser getModel() {
        return adminUser;
    }

    // 注入Service
    private AdminUserService adminUserService;
    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    // 后台登陆的方法
    public String login(){
        // 调用Service完成登陆
        AdminUser existAdminUser = adminUserService.login(adminUser);

        if(existAdminUser == null){
            // 登陆失败
            this.addActionError("亲！您的用户名或密码错误！");
            return "loginFail";
        }else {
            // 登陆成功
            ServletActionContext.getRequest().getSession().setAttribute("existAdminUser",existAdminUser);
            return "loginSuccess";
        }

    }



}
