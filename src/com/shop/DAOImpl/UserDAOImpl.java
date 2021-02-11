/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.shop.DAO.UserDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Permission;
import com.shop.model.Role;
import com.shop.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class UserDAOImpl implements UserDAO{

    private static final String TABLE_USER= "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_ROLE_ID = "role_id";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_STATUS = "status";
    
    // all queries
    private static final String INSERT_USER_QUERY = "insert into "+TABLE_USER+"("+COLUMN_USER_NAME+","+COLUMN_PASSWORD+","+COLUMN_USER_ROLE_ID+","+COLUMN_EMAIL+","+
            COLUMN_CONTACT+","+COLUMN_ADDRESS+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER_QUERY = "update "+TABLE_USER+" set "+COLUMN_USER_NAME+" = ?,"+COLUMN_PASSWORD+" =?,"+
            COLUMN_USER_ROLE_ID+" =?, "+COLUMN_EMAIL+" =?, "+COLUMN_CONTACT+" =?, "+COLUMN_ADDRESS+" =?,"+COLUMN_STATUS+" =?,"+COLUMN_CREATED_DATE+" =?,"+COLUMN_CREATED_BY+" =? where "+COLUMN_USER_ID+" = ?";
    private static final String DELETE_USER_QUERY = "update "+TABLE_USER+" set "+COLUMN_STATUS+" = 0 where "+COLUMN_USER_ID+" =?";
    private static final String GET_USER_BY_ID_QUERY = "select * from "+TABLE_USER+" where "+COLUMN_USER_ID+" =?";
    private static final String GET_ALL_USER_QUERY = "select * from "+TABLE_USER+" where "+COLUMN_STATUS+" = 1";
    private static final String GET_USER_ID_BY_NAME = "select "+COLUMN_USER_ID+" from "+TABLE_USER+" where "+COLUMN_USER_NAME+" = ?";

    
    Connection conn = DBConnection.getConnection();
    
    @Override
     public Integer addUser(User user) {
        Integer row = null;
        try {
            
            java.sql.Date createdDate = new Date(user.getCreatedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(INSERT_USER_QUERY);
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getRole().getRoleId());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getContact());
            pstmt.setString(6, user.getAddress());
            pstmt.setDate(7, createdDate);
            pstmt.setInt(8, user.getCreatedBy());
            pstmt.setInt(9, 1);
            
            
            row = pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
        public Integer updateUser(User user) {
        Integer row = null;
        try {
            
             java.sql.Date modifiedDate = new Date(user.getModifiedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_USER_QUERY);
            
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getRole().getRoleId());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getContact());
            pstmt.setString(6, user.getAddress());
            pstmt.setInt(7, 1);
            pstmt.setDate(8, modifiedDate);
            pstmt.setInt(9, user.getModifiedBy());
            pstmt.setInt(10, user.getUserId());
            
            
            row = pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

   

    @Override
    public User getUserById(Integer id) {
        User user = null;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_USER_BY_ID_QUERY);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                user  = new User();
            
                Role role = new Role();
                role.setRoleId(rs.getInt(COLUMN_USER_ROLE_ID));
                
                user.setUserId(rs.getInt(COLUMN_USER_ID));
                user.setUserName(rs.getString(COLUMN_USER_NAME));
                user.setPassword(rs.getString(COLUMN_PASSWORD));
                user.setRole(role);
                user.setEmail(rs.getString(COLUMN_EMAIL));
                user.setContact(rs.getString(COLUMN_CONTACT));
                user.setAddress(rs.getString(COLUMN_ADDRESS));
                user.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                user.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                user.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                user.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                
            }
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return user;
        
    }

    @Override
    public Integer getUserIdByName(String name) {
        Integer id = null;
        ResultSet rs = null;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(GET_USER_ID_BY_NAME);
            pstmt.setString(1, name);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
            id = rs.getInt(COLUMN_USER_ID);
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUsers = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_USER_QUERY);
            
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                User user = new User();
              
                Role role = new Role();
                
                role.setRoleId(rs.getInt(COLUMN_USER_ROLE_ID));
                
                user.setUserId(rs.getInt(COLUMN_USER_ID));
                user.setUserName(rs.getString(COLUMN_USER_NAME));
                user.setPassword(rs.getString(COLUMN_PASSWORD));
                user.setRole(role);
                user.setEmail(rs.getString(COLUMN_EMAIL));
                user.setContact(rs.getString(COLUMN_CONTACT));
                user.setAddress(rs.getString(COLUMN_ADDRESS));
                user.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                user.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                user.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                user.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                
                allUsers.add(user);
            }
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public Integer deleteUser(Integer roleId) {
        Integer row = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(DELETE_USER_QUERY);
            pstmt.setInt(1, roleId);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }
    
}
