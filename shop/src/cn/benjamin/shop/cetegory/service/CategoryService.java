package cn.benjamin.shop.cetegory.service;

import cn.benjamin.shop.cetegory.dao.CategoryDao;
import cn.benjamin.shop.cetegory.vo.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 一级分类的业务层对象
 * Created by Benjamin on 2018/12/20.
 */

@Transactional
public class CategoryService {


    // 注入 CategoryDao
    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    // 业务层查询所有一级分类的方法
    public List<Category> findall() {
        return categoryDao.findall();
    }

    // 业务层保存一级分类的房
    public void save(Category category) {
        categoryDao.save(category);
    }

    // 业务层根据cid查询一级分类
    public Category findByCid(Integer cid) {
        return categoryDao.findByCid(cid);
    }

    // 业务层删除一级分类的方法
    public void delete(Category category) {
         categoryDao.delete(category);
    }
}
