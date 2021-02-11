/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.shop.DAO.PermissionDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Permission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class PermissionDAOImpl implements PermissionDAO{

    private static final String TABLE_PERMISSION = "permission";
    private static final String COLUMN_PERMISSION_ID = "permission_id";
    private static final String COLUMN_PERMISSION_NAME = "permission";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_STATUS = "status";
    
    // all queries
    private static final String INSERT_PERMISSION_QUERY = "insert into "+TABLE_PERMISSION+"("+COLUMN_PERMISSION_NAME+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values(?,?,?,1)";
    private static final String UPDATE_PERMISSION_QUERY = "update "+TABLE_PERMISSION+" set "+COLUMN_PERMISSION_NAME+"=?,"+COLUMN_CREATED_DATE+" = ?,"+COLUMN_CREATED_BY+" =? where "+COLUMN_PERMISSION_ID+" = ?";
    private static final String DELETE_PERMISSION_QUERY = "update "+TABLE_PERMISSION+" set "+COLUMN_STATUS+" = 0 where "+COLUMN_PERMISSION_ID+" =?";
    private static final String GET_PERMISSION_BY_ID_QUERY = "select * from "+TABLE_PERMISSION+" where "+COLUMN_PERMISSION_ID+" =?";
    private static final String GET_ALL_PERMISSION_QUERY = "select * from "+TABLE_PERMISSION+" where "+COLUMN_STATUS+" = 1";
    private static final String GET_PERMISSION_ID_BY_NAME = "select "+COLUMN_PERMISSION_ID+" from "+TABLE_PERMISSION+" where "+COLUMN_PERMISSION_NAME+" = ?";
    
    // this query is for the role-permission table
    private static final String GET_ALL_PERMISSION_OF_PARTICULAR_ROLE = "select p.permission_id,p.permission from role_permission rp inner join permission p on rp.permission_id = p.permission_id where role_id = ?";

    
    Connection conn = DBConnection.getConnection();
    
    @Override
    public Integer addPermission(Permission permission) {
        Integer row = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(INSERT_PERMISSION_QUERY);
            
            java.sql.Date sqlDate = new java.sql.Date(permission.getCreatedDate().getTime());
            pstmt.setString(1, permission.getPermission());
            pstmt.setDate(2, sqlDate);
            pstmt.setInt(3, permission.getCreatedBy());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updatePermission(Permission permission) {
        Integer row = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_PERMISSION_QUERY);
            
            java.sql.Date sqlDate = new java.sql.Date(permission.getModifiedDate().getTime());
            pstmt.setString(1, permission.getPermission());
            pstmt.setDate(2, sqlDate);
            pstmt.setInt(3, permission.getModifiedBy());
            pstmt.setInt(4, permission.getPermissionId());
            
            
            row = pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

   

    @Override
    public Permission getPermissionById(Integer id) {
        Permission permission = null;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_PERMISSION_BY_ID_QUERY);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                permission = new Permission();
            
                permission.setPermissionId(rs.getInt(COLUMN_PERMISSION_ID));
                permission.setPermission(rs.getString(COLUMN_PERMISSION_NAME));
                permission.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                permission.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                permission.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                permission.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
               
            }
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return permission;
        
    }

    @Override
    public Integer getPermissionIdByName(String name) {
        Integer id = null;
        ResultSet rs = null;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(GET_PERMISSION_ID_BY_NAME);
            pstmt.setString(1, name);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
            id = rs.getInt("permission_id");
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Permission> getAllPermission() {
        List<Permission> allPermissions = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_PERMISSION_QUERY);
            
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                Permission permission = new Permission();
                
                permission.setPermissionId(rs.getInt(COLUMN_PERMISSION_ID));
                permission.setPermission(rs.getString(COLUMN_PERMISSION_NAME));
                permission.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                permission.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                permission.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                permission.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                
                
                allPermissions.add(permission);
            }
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return allPermissions;
    }

    @Override
    public Integer deletePermission(Integer permissionId) {
        Integer row = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(DELETE_PERMISSION_QUERY);
            pstmt.setInt(1, permissionId);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }
    
    
    public List<Permission> getPermissionOf(Integer roleId){
           List<Permission> allPermission = new ArrayList<>();
           ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_PERMISSION_OF_PARTICULAR_ROLE);
            pstmt.setInt(1, roleId);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
            
                Permission p = new Permission();
                p.setPermissionId(rs.getInt("p.permission_id"));
                p.setPermission(rs.getString("p.permission"));
                
                allPermission.add(p);
            }
            
            
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
    
        return allPermission;
    }
}
