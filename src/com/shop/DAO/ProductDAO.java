/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAO;

import com.shop.model.Product;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public interface ProductDAO {
    public Integer addProduct(Product product);
    public Integer updateProduct(Product product);
    public Integer deleteProduct(Integer orderID);
    public Product getProductByID(Integer orderID);
    public Integer getProductIDByName(String productName);
    public List<Product> getAllProduct();
    public Integer reduceProduct(Product product, int reduceQuantity);
    public Integer plusProduct(Product product, int plusQuantity);
    public Integer productQuantity(Integer productID);
    
}
