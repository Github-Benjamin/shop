package cn.benjamin.shop.cetegory.dao;

import cn.benjamin.shop.cetegory.vo.Category;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Benjamin on 2018/12/20.
 */

public class CategoryDao extends HibernateDaoSupport {

    // DAO层的查询所有一级分类的方法
    public List<Category> findall() {
        String hql = "from Category";
        List<Category> list = this.getHibernateTemplate().find(hql);
        return list;
    }

}
