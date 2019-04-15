package cn.benjamin.shop.order.adminaction;

import cn.benjamin.shop.order.service.OrderService;
import cn.benjamin.shop.order.vo.Order;
import cn.benjamin.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Created by Benjamin on 2019/4/15.
 */

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {

    // 注入模型驱动
    private Order order = new Order();
    public Order getModel() {
        return order;
    }

    // 注入Service
    private OrderService orderService;
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    // 接收page
    private Integer page;
    public void setPage(Integer page) {
        this.page = page;
    }

    // 查询所有订单
    public String findAll(){
        PageBean<Order> pageBean = orderService.findByPage(page);
        // 将PageBean的数据保存到值栈中
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAll";
    }


    // 发货修改订单状态
    public String updateState(){
        // 通过oid查询订单信息
        Order currOrder = orderService.findByOid(order.getOid());
        currOrder.setState(3);
        orderService.update(currOrder);
        return "updateStateSuccess";
    }

    public String findByState(){
        // 通过State查询订单
        PageBean<Order> pageBean = orderService.findByPageState(page,order.getState());
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findByStateSuccess";
    }


}
