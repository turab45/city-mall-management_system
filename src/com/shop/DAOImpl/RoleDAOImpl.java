/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.shop.DAO.RoleDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class RoleDAOImpl implements RoleDAO{
    private static final String TABLE_ROLE = "role";
    private static final String COLUMN_ROLE_ID = "role_id";
    private static final String COLUMN_ROLE_NAME = "role_name";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_STATUS = "status";
    
    // all queries
    private static final String INSERT_ROLE_QUERY = "insert into "+TABLE_ROLE+"("+COLUMN_ROLE_NAME+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values(?,?,?,1)";
    private static final String UPDATE_ROLE_QUERY = "update "+TABLE_ROLE+" set "+COLUMN_ROLE_NAME+" = ?,"+COLUMN_MODIFIED_DATE+"=?,"+COLUMN_MODIFIED_BY+"=? where "+COLUMN_ROLE_ID+" = ?";
    private static final String DELETE_ROLE_QUERY = "update "+TABLE_ROLE+" set "+COLUMN_STATUS+" = 0 where "+COLUMN_ROLE_ID+" =?";
    private static final String GET_ROLE_BY_ID_QUERY = "select * from "+TABLE_ROLE+" where "+COLUMN_ROLE_ID+" =?";
    private static final String GET_ALL_ROLE_QUERY = "select * from "+TABLE_ROLE+" where "+COLUMN_STATUS+" = 1";
    private static final String GET_ROLE_ID_BY_NAME = "select "+COLUMN_ROLE_ID+" from "+TABLE_ROLE+" where "+COLUMN_ROLE_NAME+" = ?";

    
    Connection conn = DBConnection.getConnection();
    
    @Override
    public Integer addRole(Role role) {
        Integer row = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(INSERT_ROLE_QUERY);
            
            java.sql.Date createDate = new java.sql.Date(role.getCreatedDate().getTime());
            
            pstmt.setString(1, role.getRoleName());
            pstmt.setDate(2, createDate);
            pstmt.setInt(3, role.getCreatedBy());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateRole(Role role) {
        Integer row = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_ROLE_QUERY);
            
            java.sql.Date modifiedDate = new java.sql.Date(role.getModifiedDate().getTime());
            
            pstmt.setString(1, role.getRoleName());
            pstmt.setDate(2, modifiedDate);
            pstmt.setInt(3, role.getModifiedBy());
            pstmt.setInt(4, role.getRoleId());
            
            
            row = pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteRole(Integer roleId) {
        Integer row = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(DELETE_ROLE_QUERY);
            pstmt.setInt(1, roleId);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return row;
    }

    @Override
    public Role getRoleById(Integer id) {
        Role role = null;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ROLE_BY_ID_QUERY);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                role = new Role();
            
                role.setRoleId(rs.getInt(COLUMN_ROLE_ID));
                role.setRoleName(rs.getString(COLUMN_ROLE_NAME));
                role.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                role.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                role.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                role.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                
            }
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return role;
        
    }

    @Override
    public Integer getRoleIdByName(String name) {
        Integer id = null;
        ResultSet rs = null;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(GET_ROLE_ID_BY_NAME);
            pstmt.setString(1, name);
            
            rs = pstmt.executeQuery();
            while(rs.next()){
            id = rs.getInt(COLUMN_ROLE_ID);
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> allRoles = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_ROLE_QUERY);
            
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                Role role = new Role();
                
                role.setRoleId(rs.getInt(COLUMN_ROLE_ID));
                role.setRoleName(rs.getString(COLUMN_ROLE_NAME));
                role.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                role.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                role.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                role.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                
                allRoles.add(role);
            }
            
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        return allRoles;
    }
    
}
