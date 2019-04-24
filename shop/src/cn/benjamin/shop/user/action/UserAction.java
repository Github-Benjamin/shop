package cn.benjamin.shop.user.action;

import cn.benjamin.shop.user.vo.User;
import cn.benjamin.shop.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Benjamin on 2018/12/15.
 */
// 用户模块Action
public class UserAction extends ActionSupport implements ModelDriven<User> {


    // 模型驱动使用的对象
    private User user = new User();
    public User getModel(){
        return user;
    }

    // 接收验证码:
    private String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    // 注入UserService
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    // 跳转到注册页面的执行方法
    public String registPage(){
        return "registPage";  // 页面跳转
    }


    // AJAX 进行异步效验
    public String findByName() throws IOException {
        // 调用Service进行查询:
        User existUser = userService.findByUsername(user.getUsername());
        // 获得response对象,项页面输出:
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html; charset=UTF-8");
        // 判断
        if (existUser != null) {
            // 查询到该用户:用户名已经存在
            response.getWriter().println("<font color='red'>用户名已经存在</font>");
        } else {
            // 没查询到该用户:用户名可以使用
            response.getWriter().println("<font color='green'>用户名可以使用</font>");
        }
        return NONE;
    }


    // 用户注册的方法
    public String regist(){
        // 判断验证码程序:
        // 从session中获得验证码的随机值:
        String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
        if(!checkcode.equalsIgnoreCase(checkcode1)){
            this.addActionError("验证码输入错误!");
            return "checkcodeFail";
        }
        userService.save(user);
        this.addActionMessage("注册成功!请去邮箱激活!");
        return "msg";

    }


    // 用户激活的方法
    public String active(){
        User existUser = userService.findByUserCode( user.getCode() );
        // 判断激活码是否正确
        if( existUser == null ){
            // 激活码错误
            this.addActionMessage("激活失败：激活码错误！");
        }else {
            // 激活成功
            existUser.setState(1);
            existUser.setCode(null);
            userService.updata(existUser);
            this.addActionMessage("激活成功：请去登陆！");
        }
        return "msg";  // 页面跳转
    }


    // 跳转至登录页
    public String loginPage(){
        return "loginPage";  // 页面跳转
    }


    // 登陆方法
    public String login(){
        User existUser = userService.login(user);

        if( existUser == null ){
            // 登陆失败
            this.addActionError("登陆失败：用户名或密码错误或用户未激活！");
            return LOGIN;
        }else {
            // 登陆成功
            // 将用户的信息存入到session中
            ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
            return "loginSuccess";  // 页面跳转
        }

    }


    // 用户退出登录方法
    public String quit(){
        ServletActionContext.getRequest().getSession().invalidate();    // 销毁session
        return "quit";
    }


}
