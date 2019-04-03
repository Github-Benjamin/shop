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

    // DAO层保存一级分类的方法
    public void save(Category category) {
        this.getHibernateTemplate().save(category);
    }

    // DAO层根据cid查询一级分类的方法
    public Category findByCid(Integer cid) {
        return this.getHibernateTemplate().get(Category.class,cid);
    }

    // DAO层删除一级分类的方法
    public void delete(Category category) {
        this.getHibernateTemplate().delete(category);
    }

    // DAO层修改一级分类的方法
    public void update(Category category) {
        this.getHibernateTemplate().update(category);
    }
}
