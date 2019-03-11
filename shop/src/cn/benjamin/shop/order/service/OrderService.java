package cn.benjamin.shop.order.service;

import cn.benjamin.shop.order.dao.OrderDao;
import cn.benjamin.shop.order.vo.Order;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单模块：业务层代码
 * Created by Benjamin on 2019/3/7.
 */

@Transactional
public class OrderService {
    // 注入OrderDao
    private OrderDao orderDao;
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    // 保存订单的业务层代码
    public void save(Order order) {
        orderDao.save(order);
    }
}
