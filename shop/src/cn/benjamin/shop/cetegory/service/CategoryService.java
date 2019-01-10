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

}
