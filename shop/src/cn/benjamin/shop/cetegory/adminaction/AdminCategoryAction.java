package cn.benjamin.shop.cetegory.adminaction;

import cn.benjamin.shop.cetegory.service.CategoryService;
import cn.benjamin.shop.cetegory.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * 后台一级分类管理的Action
 * Created by Benjamin on 2019/4/2.
 */

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {
    // 模型驱动使用的类
    private Category category = new Category();
    public Category getModel() {
        return category;
    }

    // 注入一级分类的Service
    private CategoryService categoryService;
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 后台执行查询所有一级分类的方法
    public String findAll(){
        // 查询所有一级分类
        List<Category> cList = categoryService.findall();
        // 将集合的数据显示到页面
        ActionContext.getContext().getValueStack().set("cList",cList);
        return "findAll";
    }

    // 后台保存一级分类的方法
    public String save(){
        // 调用Service进行保存
        categoryService.save(category);
        // 页面跳转
        return "saveSuccess";
    }


    // 后台删除一级分类的方法
    public String delete(){
        // 接收cid，可以使用模型驱动，删除一级分类，同时删除二级分类。必须先根据id查询，再进行删除。
        category = categoryService.findByCid(category.getCid());
        // 删除
        categoryService.delete(category);
        return "deleteSuccess";

    }


}
