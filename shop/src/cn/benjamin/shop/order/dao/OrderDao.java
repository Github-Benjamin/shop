package cn.benjamin.shop.order.dao;

import cn.benjamin.shop.order.vo.Order;
import cn.benjamin.shop.utils.PageHibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 订单模块Dao层的代码
 * Created by Benjamin on 2019/3/7.
 */

public class OrderDao extends HibernateDaoSupport {
    // DAO层的保存订单的方法
    public void save(Order order) {
        this.getHibernateTemplate().save(order);
    }

    // DAO层的我的订单的个数统计
    public Integer findByCountUid(Integer uid) {
        String hql = "select count(*) from Order o where o.user.uid = ?";
        List<Long> list= this.getHibernateTemplate().find(hql,uid);
        if(  list != null && list.size()>0 ){
            return list.get(0).intValue();
        }
        return null;
    }

    // DAO层我的订单的查询
    public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
        String hql = "from Order o where o.user.uid = ? order by ordertime desc";
        List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,new Object[]{uid},begin,limit));
        return list;
    }
}
