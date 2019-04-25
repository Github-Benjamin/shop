package cn.benjamin.shop.user.adminaction;

import cn.benjamin.shop.user.service.UserService;
import cn.benjamin.shop.user.vo.User;
import cn.benjamin.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created by Benjamin on 2019/4/25.
 */

public class AdminBeforeUserAction extends ActionSupport implements ModelDriven<User> {
    // 模型驱动使用的对象
    private User user = new User();
    public User getModel() {
        return user;
    }

    // 注入用户Service
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // 接收page
    private Integer page;
    public void setPage(Integer page) {
        this.page = page;
    }

    // 查询所有用户的方法
    public String findAll(){
        PageBean<User> pageBean = userService.findByPage(page);
        // 将PageBean的数据保存到值栈中
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAll";
    }

    // 删除用户的方法
    public String delete(){
        userService.delete(user);
        return "deleteSuccess";
    }



}
