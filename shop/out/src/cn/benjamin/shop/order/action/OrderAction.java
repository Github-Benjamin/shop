package cn.benjamin.shop.order.action;

import cn.benjamin.shop.order.service.OrderService;
import cn.benjamin.shop.order.vo.Order;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单管理的Action
 * Created by Benjamin on 2019/3/7.
 */

public class OrderAction extends ActionSupport implements ModelDriven<Order>{

    // 模型驱动使用的对象
    private Order order = new Order();
    public Order getModel() {
        return order;
    }

    // 注入OrderService
    private OrderService orderService;
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
