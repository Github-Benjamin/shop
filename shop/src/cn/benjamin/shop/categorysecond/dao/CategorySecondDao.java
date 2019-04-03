package cn.benjamin.shop.categorysecond.dao;

import cn.benjamin.shop.categorysecond.vo.CategorySecond;
import cn.benjamin.shop.utils.PageHibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * 二级分类管理的Dao层的类
 * Created by Benjamin on 2019/1/5.
 */

public class CategorySecondDao extends HibernateDaoSupport {

    // DAO层的统计二级分类的个数方法
    public int findCount() {
        String hql = "select count(*) from CategorySecond";
        List<Long> list= this.getHibernateTemplate().find(hql);
        if(list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    // DAO层分页查询二级分类的方法
    public List<CategorySecond> findByPage(int begin,int limit) {
        String hql = "from CategorySecond order by csid desc";
        List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql,null,begin,limit));
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }
}
