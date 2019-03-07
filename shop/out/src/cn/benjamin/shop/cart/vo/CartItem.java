package cn.benjamin.shop.cart.vo;

import cn.benjamin.shop.product.vo.Product;

/**
 * 购物项对象
 * Created by Benjamin on 2019/3/6.
 */

public class CartItem {

    private Product product;
    private int count;
    private double subtotal;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // 小计自动计算
    public double getSubtotal() {
        return count * product.getShop_price();
    }

//    public void setSubtotal(double subtotal) {
//        this.subtotal = subtotal;
//    }
}
