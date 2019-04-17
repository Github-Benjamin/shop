package cn.benjamin.shop.order.action;

import cn.benjamin.shop.cart.vo.Cart;
import cn.benjamin.shop.cart.vo.CartItem;
import cn.benjamin.shop.order.service.OrderService;
import cn.benjamin.shop.order.vo.Order;
import cn.benjamin.shop.order.vo.OrderItem;
import cn.benjamin.shop.user.vo.User;
import cn.benjamin.shop.utils.PageBean;
import cn.benjamin.shop.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
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

    // 接收page参数
    private Integer page;
    public void setPage(Integer page) {
        this.page = page;
    }

    // 接收支付通道编码
    private String pd_FrpId;
    public void setPd_FrpId(String pd_FrpId) {
        this.pd_FrpId = pd_FrpId;
    }

    // 接收付款成功后的响应数据
    private String r6_Order;
    private String r3_Amt;
    public void setR6_Order(String r6_Order) {
        this.r6_Order = r6_Order;
    }
    public void setR3_Amt(String r3_Amt) {
        this.r3_Amt = r3_Amt;
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
        // 通过值栈的方式进行显示；因为Order显示的对象就是模型驱动的使用对象。
        // 清空购物车
        cart.clearCart();
        return "saveSuccess";

    }

    // 我的订单的查询
    public String findByUid(){
        // 根据用户的id查询
        User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        // 调用Service
        PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
        // 将分页数据显示到页面上
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findByUidSuccess";

    }

    // 根据订单的id查询订单的方法
    public String findByOid(){
        order = orderService.findByOid(order.getOid());
        return "findByOidSuccess";
    }



    // 获取时间解决bug
    public static Date getNowDate() throws java.text.ParseException  {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Date date1=formatter.parse(dateString);
        return date1;
    }


    // 为订单付款的方法
    public String payOrder(){
        // 修改订单
        Order currOrder = orderService.findByOid(order.getOid());
        currOrder.setAddr(order.getAddr());
        currOrder.setName(order.getName());
        currOrder.setPhone(order.getPhone());
        orderService.update(currOrder);

        // 为订单付款
        String p0_Cmd = "Buy"; // 业务类型
        String p1_MerId = "10001126856";// 商户id编号
        String p2_Order = order.getOid().toString();// 订单编号
        String p3_Amt = "0.01";// 支付金额
        String p4_Cur = "CNY"; // 交易币种
        String p5_Pid = ""; // 商品名称
        String p6_Pcat = ""; // 商品种类
        String p7_Pdesc = ""; // 商品描述
        // 支付成功后,当用户点击返回商家时跳转的页面
        String p8_Url = "http://localhost:8080/order_callBack.action";
        String p9_SAF = ""; // 送货地址
        String pa_MP = ""; // 扩展信息
        String pd_Frpid = this.pd_FrpId; // 支付通道编码
        String pr_NeedResponse = "1"; // 应答机制
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
                p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

        // 向易宝发送请求
        StringBuffer stringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
        stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
        stringBuffer.append("p2_Order=").append(p2_Order).append("&");
        stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
        stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
        stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
        stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
        stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        stringBuffer.append("p8_Url=").append(p8_Url).append("&");
        stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
        stringBuffer.append("pd_Frpid=").append(pd_Frpid).append("&");
        stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        stringBuffer.append("hmac=").append(hmac);

        // 重定向到易宝
        try {
            ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    // 付款成功后的转向
    public String callBack(){
        // 修改订单状态：修改状态未已经付款。
        Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
        currOrder.setState(2);
        orderService.update(currOrder);
        // 在页面显示付款成功信息！
        this.addActionMessage("订单付款成功：订单编号：" + r6_Order + "付款进入：" + r3_Amt);
        return "meg";
    }

    // 发货后的确认收货操作
    public String updateState(){
        Order currOrder = orderService.findByOid(order.getOid());
        currOrder.setState(4);
        orderService.update(currOrder);
        return "updateState";
    }

}
