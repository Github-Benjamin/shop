package cn.benjamin.shop.order.service;

import cn.benjamin.shop.order.dao.OrderDao;
import cn.benjamin.shop.order.vo.Order;
import cn.benjamin.shop.order.vo.OrderItem;
import cn.benjamin.shop.utils.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    // 我的订单业务层的代码
    public PageBean<Order> findByPageUid(Integer uid, Integer page) {
        PageBean<Order> pageBean =  new PageBean<Order>();
        // 设置当前页数
        pageBean.setPage(page);
        // 设置每页显示的记录数
        Integer limit = 5;
        pageBean.setLimit(limit);
        // 设置总记录数
        Integer totalCount = null;
        totalCount = orderDao.findByCountUid(uid);
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        Integer totalPage = null;
        if( totalCount % limit == 0 ){
            totalPage = totalCount / limit;
        }else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        // 设置每页显示数据集合
        Integer begin = ( page - 1 ) * limit;
        List<Order> list = orderDao.findByPageUid(uid,begin,limit);
        pageBean.setList(list);

        return pageBean;
    }

    // 业务层：根据订单id查询订单
    public Order findByOid(Integer oid) {
        return orderDao.findByOid(oid);
    }

    // 业务层：修改订单的操作
    public void update(Order currOrder) {
        orderDao.updata(currOrder);
    }

    // 查询所有订单
    public PageBean<Order> findByPage(Integer page) {
        PageBean<Order> pageBean =  new PageBean<Order>();
        // 设置当前页数
        pageBean.setPage(page);
        // 设置每页显示的记录数
        Integer limit = 10;
        pageBean.setLimit(limit);
        // 设置总记录数
        Integer totalCount = null;
        totalCount = orderDao.findByCount();
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        Integer totalPage = null;
        if( totalCount % limit == 0 ){
            totalPage = totalCount / limit;
        }else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        // 设置每页显示数据集合
        int begin = ( page - 1 ) * limit;
        List<Order> list = orderDao.findByPage(begin,limit);
        pageBean.setList(list);

        return pageBean;
    }

    // 根据订单状态查询订单
    public PageBean<Order> findByPageState(Integer page, Integer state) {
        PageBean<Order> pageBean =  new PageBean<Order>();
        // 设置当前页数
        pageBean.setPage(page);
        // 设置每页显示的记录数
        Integer limit = 10;
        pageBean.setLimit(limit);
        // 设置总记录数
        Integer totalCount = null;
        totalCount = orderDao.findByCountState(state);
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        Integer totalPage = null;
        if( totalCount % limit == 0 ){
            totalPage = totalCount / limit;
        }else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        // 设置每页显示数据集合
        int begin = ( page - 1 ) * limit;
        List<Order> list = orderDao.findByPageState(state,begin,limit);
        pageBean.setList(list);

        return pageBean;

    }

    // 业务层根据订单id查询订单项的方法
    public List<OrderItem> findOrderItem(Integer oid) {
        return orderDao.findOrderItem(oid);
    }
}
