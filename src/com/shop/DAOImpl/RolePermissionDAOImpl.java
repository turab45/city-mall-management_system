/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.shop.DAO.RolePermissionDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Permission;
import com.shop.model.Role;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author Turab Bajeer
 */
public class RolePermissionDAOImpl implements RolePermissionDAO{
    Connection conn = DBConnection.getConnection();

    @Override
    public Integer addRollPermission(Role r, Permission p) {
        Integer row = null;
        try {
            String query = "insert into role_permission (role_id,permission_id,created_date,created_by,status) values(?,?,?,?,1)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, r.getRoleId());
            pstmt.setInt(2, p.getPermissionId());
            pstmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setInt(4, 0);
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row; 
    }

    @Override
    public Integer deleteRollPermission(Integer id) {
        Integer row = null;
        try {
            String query = "delete from  role_permission where role_permission_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, id);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row; 
    }

    @Override
    public Integer getRolePermissionId(Role r, Permission p) {
                Integer id = null;
                ResultSet rs = null;
        try {
            String query = "select role_permission_id from  role_permission where role_id = ? and permission_id =?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, r.getRoleId());
            pstmt.setInt(2, p.getPermissionId());
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                id = rs.getInt("role_permission_id");
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return id; 

    }
    
}
