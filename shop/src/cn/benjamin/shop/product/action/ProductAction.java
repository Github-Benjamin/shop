package cn.benjamin.shop.product.action;

import cn.benjamin.shop.cetegory.service.CategoryService;
import cn.benjamin.shop.product.service.ProductService;
import cn.benjamin.shop.product.vo.Product;
import cn.benjamin.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品的Action对象
 * Created by Benjamin on 2018/12/23.
 */

public class ProductAction extends ActionSupport implements ModelDriven<Product> {


    // 用于接收数据的模型驱动
    private Product product = new Product();
    public Product getModel(){
        return product;
    }


    // 接收分类cid
    private Integer cid;
    public void setCid(Integer cid) {
        this.cid = cid;
    }
    public Integer getCid() {
        return cid;
    }

    // 接收二级分类id
    private Integer csid;
    public Integer getCsid() {
        return csid;
    }
    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    // 注入一级分类的Service
    private CategoryService categoryService;
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 接收当前页数
    private int page;
    public void setPage(int page) {
        this.page = page;
    }


    // 注入商品的Service
    private ProductService productService;
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    // 根据商品的ID进行查询商品：执行方法；
    public String findByPid(){
        // 调用Service的方法完成查询
        product = productService.findByPid(product.getPid());
        return "findByPid";
    }


    // 根据分类的id查询商品
    public String findByCid(){
        // List<Category> cList = categoryService.findall();
        PageBean<Product> pageBean= productService.findByPageCid(cid,page); // 根据一级分类查询商品，带分页查询

        // 将PageBean存入到值栈中:
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);

        return "findByCid";
    }


    // 根据二级分类的id查询商品
    public String findByCsid(){
        //
        PageBean<Product> pageBean = productService.findByPageCsid(csid,page);

        // 将PageBean存入到值栈中:
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);

        return "findByCsid";
    }


}
