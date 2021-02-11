/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAO;

import com.shop.model.Category;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public interface CategoryDAO {
    public Integer addCategory(Category category);
    public Integer updateCategory(Category category);
    public Integer deleteCategory(Integer categoryID);
    public Category getCategoryByID(Integer categoryID);
    public Integer getCategoryIDByName(String categoryName);
    public List<Category> getAllCategory();
    public boolean checkCategory(String category);
}
