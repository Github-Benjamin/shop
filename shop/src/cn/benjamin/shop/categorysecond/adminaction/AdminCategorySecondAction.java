package cn.benjamin.shop.categorysecond.adminaction;

import cn.benjamin.shop.categorysecond.service.CategorySecondService;
import cn.benjamin.shop.categorysecond.vo.CategorySecond;
import cn.benjamin.shop.cetegory.service.CategoryService;
import cn.benjamin.shop.cetegory.vo.Category;
import cn.benjamin.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * 后台二级分类管理的Action
 * Created by Benjamin on 2019/4/3.
 */

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
    // 模型驱动使用的对象
    private CategorySecond categorySecond = new CategorySecond();
    public CategorySecond getModel() {
        return categorySecond;
    }
    // 注入二级分类的Service
    private CategorySecondService categorySecondService;
    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }

    // 接收page
    private Integer page;
    public void setPage(Integer page) {
        this.page = page;
    }

    // 注入一级分类的Service
    private CategoryService categoryService;
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 查询二级分类的方法
    public String findAll(){
        PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
        // 将PageBean的数据保存到值栈中
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAll";
    }

    // 跳转到添加页面
    public String addPage(){
        // 查询所有一级分类
        List<Category> cList = categoryService.findAll();
        // 将集合存入到值栈中.
        ActionContext.getContext().getValueStack().set("cList", cList);
        // 页面跳转
        return "addPageSuccess";
    }

    // 保存二级分类的方法
    public String save() {
        categorySecondService.save(categorySecond);
        return "saveSuccess";
    }

    // 删除二级分类的方法
    public String delete(){
        // 如果级联删除，先查询在删除，配置cascade
        categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
        categorySecondService.delete(categorySecond);
        return "deleteSuccess";
    }

}
