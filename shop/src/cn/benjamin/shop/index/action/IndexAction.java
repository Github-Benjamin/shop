package cn.benjamin.shop.index.action;

import cn.benjamin.shop.cetegory.service.CategoryService;
import cn.benjamin.shop.cetegory.vo.Category;
import cn.benjamin.shop.product.service.ProductService;
import cn.benjamin.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by Benjamin on 2018/12/14.
 */

// 首页访问Action

public class IndexAction extends ActionSupport {


    // 注入一级分类Service
    private CategoryService categoryService;
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 注入商品的Service
    private ProductService productService;
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    // 执行的访问首页的方法
    public String execute(){

        // 查询所有一级分类集合
        List<Category> cList = categoryService.findall();
        // 将一级分类存入到Session的范围:
        ActionContext.getContext().getSession().put("cList", cList);

        // 查询热门商品
        List<Product> hList = productService.findHot();
        // 保存到值栈中
        ActionContext.getContext().getValueStack().set("hList",hList);

        // 查询最新的商品
        List<Product> nList=productService.findNew();
        // 保存到值栈中
        ActionContext.getContext().getValueStack().set("nList",nList);

        return "index";
    }


}
