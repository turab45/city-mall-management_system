/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.mysql.jdbc.Connection;
import com.shop.DAO.VendorDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Vendor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class VendorDAOImpl implements VendorDAO {

    // table and column names
    private static final String TABLE_VENDOR = "vendor";
    private static final String COLUMN_VENDOR_ID = "vendor_id";
    private static final String COLUMN_VENDOR_NAME = "vendor_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_STATUS = "status";

    //queries
    private static final String ADD_VENDOR = "insert into " + TABLE_VENDOR + "(" + COLUMN_VENDOR_NAME + "," + COLUMN_EMAIL + "," + COLUMN_ADDRESS + "," + COLUMN_CONTACT + "," + COLUMN_REMARKS + "," + COLUMN_CREATED_DATE + "," + COLUMN_CREATED_BY + "," + COLUMN_STATUS + ") values (?,?,?,?,'None',?,?,1)";
    private static final String UPDATE_VENDOR = "update " + TABLE_VENDOR + " set " + COLUMN_VENDOR_NAME + "=?, " + COLUMN_EMAIL + "=?," + COLUMN_ADDRESS + "=?," + COLUMN_CONTACT + "=?," + COLUMN_REMARKS + "=?," + COLUMN_MODIFIED_DATE + "=?," + COLUMN_MODIFIED_BY + "=? where " + COLUMN_VENDOR_ID + "=?";
    private static final String DELETE_VENDOR = "update " + TABLE_VENDOR + " set " + COLUMN_STATUS + "=0 where " + COLUMN_VENDOR_ID + " =?";
    private static final String GET_VENDOR_BY_ID = "select * from " + TABLE_VENDOR + " where " + COLUMN_VENDOR_ID + " =?";
    private static final String GET_VENDOR_ID_BY_NAME = "select " + COLUMN_VENDOR_ID + " from " + TABLE_VENDOR + " where " + COLUMN_VENDOR_NAME + " =? ";
    private static final String GET_ALL_VENDOR = "select * from " + TABLE_VENDOR;

    Connection conn = DBConnection.getConnection();

    @Override
    public Integer addVendor(Vendor vendor) {
        Integer row = null;
        try {
            
            java.sql.Date createdDate = new java.sql.Date(vendor.getCreatedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(ADD_VENDOR);
            pstmt.setString(1, vendor.getVendorName());
            pstmt.setString(2, vendor.getEmail());
            pstmt.setString(3, vendor.getAddress());
            pstmt.setString(4, vendor.getContact());
            pstmt.setDate(5, createdDate);
            pstmt.setInt(6, vendor.getCreatedBy());

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateVendor(Vendor vendor) {
        Integer row = null;
        try {
            
            java.sql.Date createdDate = new java.sql.Date(vendor.getModifiedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_VENDOR);
            pstmt.setString(1, vendor.getVendorName());
            pstmt.setString(2, vendor.getEmail());
            pstmt.setString(3, vendor.getAddress());
            pstmt.setString(4, vendor.getContact());
            pstmt.setString(5, vendor.getRemarks());
            pstmt.setDate(6, createdDate);
            pstmt.setInt(7, vendor.getCreatedBy());
            pstmt.setInt(8, vendor.getVendorID());

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteVendor(Integer vendorID) {
        Integer row = null;
        try {
            
            PreparedStatement pstmt = conn.prepareStatement(DELETE_VENDOR);
            pstmt.setInt(1, vendorID);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Vendor getVendorByID(Integer vendorID) {
        Vendor vendor = null;
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_VENDOR_BY_ID);
            pstmt.setInt(1, vendorID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                vendor = new Vendor();
                
                vendor.setVendorID(rs.getInt(COLUMN_VENDOR_ID));
                vendor.setVendorName(rs.getString(COLUMN_VENDOR_NAME));
                vendor.setEmail(rs.getString(COLUMN_EMAIL));
                vendor.setAddress(rs.getString(COLUMN_ADDRESS));
                vendor.setContact(rs.getString(COLUMN_CONTACT));
                vendor.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                vendor.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                vendor.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                vendor.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                vendor.setRemarks(rs.getString(COLUMN_REMARKS));
                vendor.setVendorAccount(rs.getString("vendor_account"));
                vendor.setVendorAccountBalance(rs.getDouble("vendor_account_balance"));
                
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        
        return vendor;
    }

    @Override
    public Integer getVendorIDByName(String vendorName) {
        Integer id = null;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_VENDOR_ID_BY_NAME);
            pstmt.setString(1, vendorName);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                id = rs.getInt(COLUMN_VENDOR_ID);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Vendor> getAllVendor() {
       List<Vendor> allVendor = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_VENDOR);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            
                Vendor vendor = new Vendor();
                
                vendor.setVendorID(rs.getInt(COLUMN_VENDOR_ID));
                vendor.setVendorName(rs.getString(COLUMN_VENDOR_NAME));
                vendor.setEmail(rs.getString(COLUMN_EMAIL));
                vendor.setAddress(rs.getString(COLUMN_ADDRESS));
                vendor.setContact(rs.getString(COLUMN_CONTACT));
                vendor.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                vendor.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                vendor.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                vendor.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                vendor.setRemarks(rs.getString(COLUMN_REMARKS));
                
                
                allVendor.add(vendor);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        
        return allVendor;
    }

}
