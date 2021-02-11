/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.shop.DAO.OrderDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Order;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class OrderDAOImpl implements OrderDAO{
    
    // table and columns
    private static final String TABLE_ORDER = "order";
    private static final String COLUMN_ORDER_ID = "order_id";
    private static final String COLUMN_ORDER_CODE = "order_code";
    private static final String COLUMN_ORDER_DATE = "order_date";
    private static final String COLUMN_CUSTOMER_NAME = "customer_name";
    private static final String COLUMN_CUSTOMER_ADDRESS = "customer_address";
    private static final String COLUMN_GRAND_TOTAL = "grand_total";
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_STATUS = "status";
    
    
    //queries
    private static final String ADD_ORDER_QUERY= "insert into `"+TABLE_ORDER+"` ("+COLUMN_ORDER_CODE+","+COLUMN_CUSTOMER_NAME+","+COLUMN_ORDER_DATE+","+COLUMN_CUSTOMER_ADDRESS+","+COLUMN_GRAND_TOTAL+","+COLUMN_REMARKS+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values (?,?,?,?,?,?,?,?,1)";
    private static final String UPDATE_ORDER_QUERY = "update `"+TABLE_ORDER+"` set "+COLUMN_CUSTOMER_NAME+"=?,"+COLUMN_CUSTOMER_ADDRESS+"=?,"+COLUMN_GRAND_TOTAL+"=?,"+COLUMN_REMARKS+"=?,"+COLUMN_MODIFIED_DATE+"=?,"+COLUMN_MODIFIED_BY+"=? where "+COLUMN_ORDER_ID+"=?";
    private static final String DELETE_ORDER_QUERY = "update `"+TABLE_ORDER+"` set "+COLUMN_STATUS+"=0 where "+COLUMN_ORDER_ID+" =?";
    private static final String GET_ORDER_BY_ID_QUERY = "select * from `"+TABLE_ORDER+"` where "+COLUMN_ORDER_ID+" =?";
    private static final String GET_ORDER_ID_BY_CODE_QUERY = "select "+COLUMN_ORDER_ID+" from `"+TABLE_ORDER+"` where "+COLUMN_ORDER_CODE+" =? ";
    private static final String GET_ALL_ORDER_QUERY = "select * from `"+TABLE_ORDER+"` where "+COLUMN_STATUS+" =1";
    
    
    // connection
    Connection conn = DBConnection.getConnection();

    @Override
    public Integer addOrder(Order order) {
        Integer row = null;
        try {
            
            java.sql.Date sqlDate = new java.sql.Date(order.getCreatedDate().getTime());
            
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(ADD_ORDER_QUERY);
            pstmt.setString(1, order.getOrderCode());
            pstmt.setString(2, order.getCustomerName());
            pstmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            pstmt.setString(4, order.getCustomerAddress());
            pstmt.setDouble(5, order.getGrandTotal());
            pstmt.setString(6, order.getRemarks());
            pstmt.setDate(7, sqlDate);
            pstmt.setInt(8, order.getCreatedBy());
            
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR IN ADD ACCOUNT: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateOrder(Order order) {
        Integer row = null;
        try {
            
            java.sql.Date sqlDate = new java.sql.Date(order.getModifiedDate().getTime());
            
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(UPDATE_ORDER_QUERY);
            pstmt.setString(1, order.getCustomerName());
            pstmt.setString(2, order.getCustomerAddress());
            pstmt.setDouble(3, order.getGrandTotal());
            pstmt.setString(4, order.getRemarks());
            pstmt.setDate(5, sqlDate);
            pstmt.setInt(6, order.getModifiedBy());
            pstmt.setInt(7, order.getOrderID());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR IN UPDATE ACCOUNT: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteOrder(Integer orderID) {
        Integer row = null;
        try {
            
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(DELETE_ORDER_QUERY);
            pstmt.setInt(1, orderID);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            
            System.out.println("ERROR IN DELETE ACCOUNT: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Order getOrderByID(Integer orderID) {
        Order order = null;
        ResultSet rs = null;
        
        try {
           
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(GET_ORDER_BY_ID_QUERY);
            pstmt.setInt(1, orderID);
            
            rs = (ResultSet) pstmt.executeQuery();
            
            if(rs.next()){
            
                order = new Order();
                
                order.setOrderID(rs.getInt(COLUMN_ORDER_ID));
                order.setOrderCode(rs.getString(COLUMN_ORDER_CODE));
                order.setCustomerName(rs.getString(COLUMN_CUSTOMER_NAME));
                order.setCustomerAddress(rs.getString(COLUMN_CUSTOMER_ADDRESS));
                order.setOrderDate(rs.getDate(COLUMN_ORDER_DATE));
                order.setGrandTotal(rs.getDouble(COLUMN_GRAND_TOTAL));
                order.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                order.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                order.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                order.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                order.setRemarks(rs.getString(COLUMN_REMARKS));
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return order;
    }

    @Override
    public Integer getOrderIDByCode(String orderCode) {
        Integer id = null;
        ResultSet rs = null;
        try {
                
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(GET_ORDER_ID_BY_CODE_QUERY);
            pstmt.setString(1, orderCode);
            
            rs = (ResultSet) pstmt.executeQuery();
            
            if(rs.next()){
            
                id = rs.getInt(COLUMN_ORDER_ID);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> allOrders = new ArrayList<>();
        ResultSet rs = null;
        
        try {
           
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(GET_ALL_ORDER_QUERY);
            
            rs = (ResultSet) pstmt.executeQuery();
            
            while(rs.next()){
                Order order = new Order();
            
                order.setOrderID(rs.getInt(COLUMN_ORDER_ID));
                order.setOrderCode(rs.getString(COLUMN_ORDER_CODE));
                order.setCustomerName(rs.getString(COLUMN_CUSTOMER_NAME));
                order.setCustomerAddress(rs.getString(COLUMN_CUSTOMER_ADDRESS));
                order.setGrandTotal(rs.getDouble(COLUMN_GRAND_TOTAL));
                order.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                order.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                order.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                order.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                order.setRemarks(rs.getString(COLUMN_REMARKS));
                order.setOrderDate(rs.getDate(COLUMN_ORDER_DATE));
                
                allOrders.add(order);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return allOrders;
    }
    
}
