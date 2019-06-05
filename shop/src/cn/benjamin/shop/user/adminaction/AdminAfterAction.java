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

    // 添加后台用户的方法
    public String addPage(){
        return "addPageSuccess";
    }

    // 存储用户的方法
    public String save(){
        adminUserService.adminAfterSave(adminUser);
        return "saveSuccess";
    }

    // 编辑前台用户的方法
    public String edit(){
        // 根据用户id查询用户信息
        adminUser = adminUserService.findByUid(adminUser.getUid());
        // 通过值栈进行保存数据
        ActionContext.getContext(). getValueStack().set("adminUser",adminUser);
        return "editSuccess";
    }

    // 修改后台用户方法
    public String update(){
        adminUserService.update(adminUser);
        return "updateSuccess";
    }

    // 删除用户的方法
    public String delete(){
        adminUserService.delete(adminUser);
        return "deleteSuccess";
    }

}
