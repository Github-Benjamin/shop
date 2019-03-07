package cn.benjamin.shop.order.service;

import cn.benjamin.shop.order.dao.OrderDao;

/**
 * 订单模块：业务层代码
 * Created by Benjamin on 2019/3/7.
 */

public class OrderService {
    // 注入OrderDao
    private OrderDao orderDao;
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }



}
