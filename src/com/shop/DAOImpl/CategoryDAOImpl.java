/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.mysql.jdbc.PreparedStatement;
import com.shop.DAO.CategoryDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Category;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class CategoryDAOImpl implements CategoryDAO{
    
    // table and columns
    private static final String TABLE_CATEGORY = "category";
    private static final String COLUMN_CATEGORY_ID = "category_id";
    private static final String COLUMN_CATEGORY_NAME = "category";
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_STATUS = "status";
    
    // all queries
    private static final String ADD_CATEGORY = "insert into "+TABLE_CATEGORY+"("+COLUMN_CATEGORY_NAME+","+COLUMN_REMARKS+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values (?,?,?,?,1)";
    private static final String UPDATE_CATEGORY = "update "+TABLE_CATEGORY+" set "+COLUMN_CATEGORY_NAME+"=?, "+COLUMN_REMARKS+"=?,"+COLUMN_MODIFIED_DATE+"=?,"+COLUMN_MODIFIED_BY+"=? where "+COLUMN_CATEGORY_ID+"=?";
    private static final String DELETE_CATEGORY = "delete from "+TABLE_CATEGORY+" where "+COLUMN_CATEGORY_ID+" =?";
    private static final String GET_CATEGORY_BY_ID = "select * from "+TABLE_CATEGORY+" where "+COLUMN_CATEGORY_ID+" =?";
    private static final String GET_CATEGORY_ID_BY_NAME = "select "+COLUMN_CATEGORY_ID+" from "+TABLE_CATEGORY+" where "+COLUMN_CATEGORY_NAME+" =? ";
    private static final String GET_ALL_CATEGORY = "select * from "+TABLE_CATEGORY;
    private static final String CHECK_CATEGORY_ALREADY_EXIST = "select "+COLUMN_CATEGORY_NAME+" from "+TABLE_CATEGORY+" where "+COLUMN_CATEGORY_NAME+" =?";

    // connection 
    Connection conn = DBConnection.getConnection();
    
    @Override
    public Integer addCategory(Category category) {
        Integer row = null;
        try {
            
            java.sql.Date sqlDate = new java.sql.Date(category.getCreatedDate().getTime());
            
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(ADD_CATEGORY);
            pstmt.setString(1, category.getCategoryName());
            pstmt.setString(2, category.getRemarks());
            pstmt.setDate(3, sqlDate);
            pstmt.setInt(4, category.getCreatedBy());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return row;
    }

    @Override
    public Integer updateCategory(Category category) {
        Integer row = null;
        try {
            java.sql.Date sqlDate = new java.sql.Date(category.getModifiedDate().getTime());
            
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(UPDATE_CATEGORY);
            pstmt.setString(1, category.getCategoryName());
            pstmt.setString(2, category.getRemarks());
            pstmt.setDate(3, sqlDate);
            pstmt.setInt(4, category.getModifiedBy());
            pstmt.setInt(5, category.getCategoryID());
            
            row = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteCategory(Integer categoryID) {
        Integer row = null;
        try {
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(DELETE_CATEGORY);
            pstmt.setInt(1, categoryID);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return row;
    }

    @Override
    public Category getCategoryByID(Integer categoryID) {
        Category category = null;
        ResultSet rs = null;
        
        try {
        
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(GET_CATEGORY_BY_ID);
            pstmt.setInt(1, categoryID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                category = new Category();
                
                category.setCategoryID(rs.getInt(COLUMN_CATEGORY_ID));
                category.setCategoryName(rs.getString(COLUMN_CATEGORY_NAME));
                category.setRemarks(rs.getString(COLUMN_REMARKS));
                category.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                category.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                category.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                category.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public Integer getCategoryIDByName(String categoryName) {
        Integer id = null;
        ResultSet rs = null;
        try {
            
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(GET_CATEGORY_ID_BY_NAME);
            pstmt.setString(1, categoryName);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                id = rs.getInt(COLUMN_CATEGORY_ID);
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return id;
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> allCategorys = new ArrayList<>();
        ResultSet rs = null;
        try {
            
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(GET_ALL_CATEGORY);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            
                Category category = new Category();
                
                category.setCategoryID(rs.getInt(COLUMN_CATEGORY_ID));
                category.setCategoryName(rs.getString(COLUMN_CATEGORY_NAME));
                category.setRemarks(rs.getString(COLUMN_REMARKS));
                category.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                category.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                category.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                category.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                
                allCategorys.add(category);
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return allCategorys;
    }

    @Override
    public boolean checkCategory(String category) {
        ResultSet rs = null;
        boolean result = false;
        try {
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(CHECK_CATEGORY_ALREADY_EXIST);
            pstmt.setString(1, category);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                result = true;
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
}
