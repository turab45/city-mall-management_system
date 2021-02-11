/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.mysql.jdbc.Connection;
import com.shop.DAO.ProductDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Category;
import com.shop.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class ProductDAOImpl implements ProductDAO{
    
    // table and column names
    private static final String TABLE_PRODUCT = "product";
    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_CATEGORY_ID = "category_id";
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_STATUS = "status";
    
    
    //queries
    private static final String ADD_PRODUCT = "insert into "+TABLE_PRODUCT+"("+COLUMN_PRODUCT_NAME+","+COLUMN_QUANTITY+","+COLUMN_PRICE+","+COLUMN_CATEGORY_ID+","+COLUMN_REMARKS+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values (?,?,?,?,?,?,?,1)";
    private static final String UPDATE_PRODUCT = "update "+TABLE_PRODUCT+" set "+COLUMN_PRODUCT_NAME+"=?, "+COLUMN_QUANTITY+"=?,"+COLUMN_PRICE+"=?,"+COLUMN_CATEGORY_ID+"=?,"+COLUMN_REMARKS+"=?,"+COLUMN_MODIFIED_DATE+"=?,"+COLUMN_MODIFIED_BY+"=? where "+COLUMN_PRODUCT_ID+"=?";
    private static final String DELETE_PRODUCT = "update "+TABLE_PRODUCT+" set "+COLUMN_STATUS+"=0 where "+COLUMN_PRODUCT_ID+" =?";
    private static final String GET_PRODUCT_BY_ID = "select * from "+TABLE_PRODUCT+" where "+COLUMN_PRODUCT_ID+" =?";
    private static final String GET_PRODUCT_ID_BY_NAME = "select "+COLUMN_PRODUCT_ID+" from "+TABLE_PRODUCT+" where "+COLUMN_PRODUCT_NAME+" =? ";
    private static final String GET_ALL_PRODUCT = "select * from "+TABLE_PRODUCT+" where "+COLUMN_STATUS+" =1 and "+COLUMN_QUANTITY+" > 0";
    private static final String REDUCE_PRODUCT = "update "+TABLE_PRODUCT+" set "+COLUMN_QUANTITY+"="+COLUMN_QUANTITY+"-? where "+COLUMN_PRODUCT_ID+"=?";
    private static final String PLUS_PRODUCT = "update "+TABLE_PRODUCT+" set "+COLUMN_QUANTITY+"="+COLUMN_QUANTITY+"+? where "+COLUMN_PRODUCT_ID+"=?";
    private static final String GET_PRODUCT_QUANTITY = "select "+COLUMN_QUANTITY+" from "+TABLE_PRODUCT+" where "+COLUMN_PRODUCT_ID+" =?";

// connection
    Connection conn= DBConnection.getConnection();
    
    @Override
    public Integer addProduct(Product product) {
        Integer row = null;
        try {
            java.sql.Date sqlDate = new java.sql.Date(product.getCreatedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(ADD_PRODUCT);
            pstmt.setString(1, product.getProductName());
            pstmt.setInt(2, product.getQuantity());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getCategory().getCategoryID());
            pstmt.setString(5, product.getRemarks());
            pstmt.setDate(6, sqlDate);
            pstmt.setInt(7, product.getCreatedBy());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateProduct(Product product) {
        Integer row = null;
        try {
            java.sql.Date sqlDate = new java.sql.Date(product.getModifiedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_PRODUCT);
            pstmt.setString(1, product.getProductName());
            pstmt.setInt(2, product.getQuantity());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getCategory().getCategoryID());
            pstmt.setString(5, product.getRemarks());
            pstmt.setDate(6, sqlDate);
            pstmt.setInt(7, product.getCreatedBy());
            pstmt.setInt(8, product.getProductID());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteProduct(Integer orderID) {
        Integer row = null;
        try {
            
            
            PreparedStatement pstmt = conn.prepareStatement(DELETE_PRODUCT);
            pstmt.setInt(1, orderID);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Product getProductByID(Integer productID) {
        Product product = null;
        ResultSet rs = null;
        try {
            
            PreparedStatement pstmt = conn.prepareStatement(GET_PRODUCT_BY_ID);
            pstmt.setInt(1, productID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                product = new Product();
                
                Category category = new Category();
                category.setCategoryID(rs.getInt(COLUMN_CATEGORY_ID));
                
                product.setProductID(rs.getInt(COLUMN_PRODUCT_ID));
                product.setProductName(rs.getString(COLUMN_PRODUCT_NAME));
                product.setQuantity(rs.getInt(COLUMN_QUANTITY));
                product.setPrice(rs.getDouble(COLUMN_PRICE));
                product.setCategory(category);
                product.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                product.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                product.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                product.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                product.setRemarks(rs.getString(COLUMN_REMARKS));
                
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Integer getProductIDByName(String productName) {
        Integer id = null;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_PRODUCT_ID_BY_NAME);
            pstmt.setString(1, productName);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                id = rs.getInt(COLUMN_PRODUCT_ID);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> allProduct = new ArrayList<>();
        ResultSet rs = null;
        try {
            
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_PRODUCT);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            
               Product product = new Product();
                
                Category category = new Category();
                category.setCategoryID(rs.getInt(COLUMN_CATEGORY_ID));
                
                product.setProductID(rs.getInt(COLUMN_PRODUCT_ID));
                product.setProductName(rs.getString(COLUMN_PRODUCT_NAME));
                product.setQuantity(rs.getInt(COLUMN_QUANTITY));
                product.setPrice(rs.getDouble(COLUMN_PRICE));
                product.setCategory(category);
                product.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                product.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                product.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                product.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                product.setRemarks(rs.getString(COLUMN_REMARKS));
                
                allProduct.add(product);
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return allProduct;
    }

    @Override
    public Integer reduceProduct(Product product, int reduceQuantity) {
        Integer row = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(REDUCE_PRODUCT);
            pstmt.setInt(1, reduceQuantity);
            pstmt.setInt(2, product.getProductID());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer plusProduct(Product product, int plusQuantity) {
        Integer row = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(PLUS_PRODUCT);
            pstmt.setInt(1, plusQuantity);
            pstmt.setInt(2, product.getProductID());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer productQuantity(Integer productID) {
        Integer quantity = null;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_PRODUCT_QUANTITY);
            pstmt.setInt(1, productID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                quantity = rs.getInt(COLUMN_QUANTITY);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return quantity;
    }
    
}
