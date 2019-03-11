package cn.benjamin.shop.order.dao;

import cn.benjamin.shop.order.vo.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 订单模块Dao层的代码
 * Created by Benjamin on 2019/3/7.
 */

public class OrderDao extends HibernateDaoSupport {
    // DAO层的保存订单的方法
    public void save(Order order) {
        this.getHibernateTemplate().save(order);
    }
}
