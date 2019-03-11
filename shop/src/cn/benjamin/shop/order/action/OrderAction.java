package cn.benjamin.shop.order.action;

import cn.benjamin.shop.cart.vo.Cart;
import cn.benjamin.shop.cart.vo.CartItem;
import cn.benjamin.shop.order.service.OrderService;
import cn.benjamin.shop.order.vo.Order;
import cn.benjamin.shop.order.vo.OrderItem;
import cn.benjamin.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    // 生成订单的方法
    public String save() throws ParseException {
        // 1. 保存数据到数据库
        // 订单数据补全
        order.setOrdertime(getNowDate());  // fix时间获取bug
        order.setState(1); // 1;未付款 2；已经付款，但是没有发货 3；已经发货，但是没有确认收货 4；交易完成
        // 总计的数据是购物车中信息
        Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
        if( cart == null ){
            this.addActionError("亲！您还没有购物！请先去购物！");
            return "msg";
        }
        order.setTotal(cart.getTotal());
        // 设置订单中的订单项
        for (CartItem cartItem:cart.getCartItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(cartItem.getCount());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }

        // 设置订单所属用户
        User existUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if( existUser == null ){
            this.addActionError("亲！您还没有登陆！请先去登陆！");
            return "login";
        }
        order.setUser(existUser);
        orderService.save(order);

        // 2. 将订单对象显示到页面上

        return "saveSuccess";
    }


    // 获取时间解决bug
    public static Date getNowDate() throws java.text.ParseException  {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Date date1=formatter.parse(dateString);
        return date1;
    }


}
