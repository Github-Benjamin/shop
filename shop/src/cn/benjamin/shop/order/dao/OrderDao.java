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

    public Order findByOid(Integer oid) {
        return this.getHibernateTemplate().get(Order.class,oid);
    }

    // DAO层的修改订单的操作
    public void updata(Order currOrder) {
        this.getHibernateTemplate().update(currOrder);
    }

    // DAO层查找所有订单
    public int findByCount() {
        String hql = "select count(*) from Order";
        List<Long> list= this.getHibernateTemplate().find(hql);
        if(list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    // DAO层分页查找分类订单的方法
    public List<Order> findByPage(int begin, Integer limit) {
        String hql = "from Order order by oid desc";
        List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,null,begin,limit));
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    // DAO层查找分类订单
    public Integer findByCountState(Integer state) {
        String hql = "select count(*) from Order Where state = ?";
        List<Long> list= this.getHibernateTemplate().find(hql,state);
        if(list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    // DAO层层分页查找分类订单的方法
    public List<Order> findByPageState(Integer state,int begin, Integer limit) {
        String hql = "from Order Where state = ? order by oid desc";
        List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,new Object[]{state},begin,limit));
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }
}
