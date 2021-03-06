package cn.benjamin.shop.categorysecond.service;

import cn.benjamin.shop.categorysecond.dao.CategorySecondDao;
import cn.benjamin.shop.categorysecond.vo.CategorySecond;
import cn.benjamin.shop.utils.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 二级分类管理的业务层类
 * Created by Benjamin on 2019/1/5.
 */
@Transactional
public class CategorySecondService {
    //注入二级分类的DAO
    private CategorySecondDao categorySecondDao;
    public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
        this.categorySecondDao = categorySecondDao;
    }

    // 业务层分析查询二级分类的方法
    public PageBean<CategorySecond> findByPage(Integer page) {
        PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
        // 设置当前的页数
        pageBean.setPage(page);
        // 设置每页显示记录数
        int limit = 10;
        pageBean.setLimit(limit);
        // 设置总记录
        int totalCount = categorySecondDao.findCount();
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        int totalPage = 0;
        if(totalCount % limit == 0 ){
            totalPage = totalCount / limit;
        }else {
            totalPage = totalCount / limit +1;
        }
        pageBean.setTotalPage(totalPage);
        // 设置每页显示数据集合
        int begin = (page -1)*limit;
        List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
    }

    // 业务层保存二级分类的方法
    public void save(CategorySecond categorySecond) {
        categorySecondDao.save(categorySecond);
    }


    // 业务层根据二级分类的id查询二级分类
    public CategorySecond findByCsid(Integer csid) {
        return  categorySecondDao.findByCsid(csid);
    }

    // 业务层删除二级分类方法
    public void delete(CategorySecond categorySecond) {
        categorySecondDao.delete(categorySecond);
    }

    // 业务层修改二级分类方法
    public void update(CategorySecond categorySecond) {
        categorySecondDao.update(categorySecond);
    }

    // 业务层查询所有二级分类的方法
    public List<CategorySecond> findAll() {
        return categorySecondDao.findAll();
    }
}
