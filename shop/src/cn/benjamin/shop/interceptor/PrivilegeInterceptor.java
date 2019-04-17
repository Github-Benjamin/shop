package cn.benjamin.shop.interceptor;

import cn.benjamin.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * Created by Benjamin on 2019/4/17.
 */

public class PrivilegeInterceptor extends MethodFilterInterceptor {
    @Override
    // 执行拦截的方法
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        // 判断session中是否保存了用户逇信息
        AdminUser existAdminUser =(AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
       if( existAdminUser == null ){
           // 没有登录进行访问
           ActionSupport actionSupport =(ActionSupport) actionInvocation.getAction();
           actionSupport.addActionError("亲！您还没有登录！没有权限访问！");
           return ActionSupport.LOGIN;

       } else {
           // 已登录过
           return actionInvocation.invoke();
       }


    }
}
