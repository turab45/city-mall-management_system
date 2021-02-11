/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.mysql.jdbc.Connection;
import com.shop.DAO.OrderDetailsDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Order;
import com.shop.model.OrderDetails;
import com.shop.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class OrderDetailsDAOImpl implements OrderDetailsDAO{
    
    // table and columns
    private static final String TABLE_ORDER_DETAILS = "order_details";
    private static final String COLUMN_ORDER_DETAILS_ID = "order_details_id";
    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_SELLING_PRICE = "selling_price";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_TOTAL = "total";
    private static final String COLUMN_ORDER_CODE = "order_code";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_STATUS = "status";
    
    
    //queries
    private static final String ADD_ORDER_DETAILS_QUERY= "insert into "+TABLE_ORDER_DETAILS+"("+COLUMN_PRODUCT_ID+","+COLUMN_SELLING_PRICE+","+COLUMN_QUANTITY+","+COLUMN_TOTAL+","+COLUMN_ORDER_CODE+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values (?,?,?,?,?,?,?,1)";
    private static final String UPDATE_ORDER_DETAILS_QUERY = "update "+TABLE_ORDER_DETAILS+" set "+COLUMN_PRODUCT_ID+"=?, "+COLUMN_SELLING_PRICE+"=?,"+COLUMN_QUANTITY+"=?,"+COLUMN_TOTAL+"=?,"+COLUMN_ORDER_CODE+"=?,"+COLUMN_MODIFIED_DATE+"=?,"+COLUMN_MODIFIED_BY+"=? where "+COLUMN_ORDER_DETAILS_ID+"=?";
    private static final String DELETE_ORDER_DETAILS_QUERY = "update "+TABLE_ORDER_DETAILS+" set "+COLUMN_STATUS+" =0 where "+COLUMN_ORDER_DETAILS_ID+" =?";
    private static final String GET_ORDER_DETAILS_BY_ID_QUERY = "select * from "+TABLE_ORDER_DETAILS+" where "+COLUMN_ORDER_DETAILS_ID+" =?";
    private static final String GET_ORDER_DETAILS_ID_BY_ORDER_ID_QUERY = "select "+COLUMN_ORDER_DETAILS_ID+" from "+TABLE_ORDER_DETAILS+" where "+COLUMN_ORDER_DETAILS_ID+" =? ";
    private static final String GET_ALL_ORDER_DETAILS_QUERY = "select * from "+TABLE_ORDER_DETAILS+" where "+COLUMN_STATUS+" =1";
    private static final String GET_ALL_ORDER_DETAILS_OF = "select * from "+TABLE_ORDER_DETAILS+" where "+COLUMN_STATUS+" =1 and "+COLUMN_ORDER_CODE+"=?";
    
    // connection
    Connection conn = DBConnection.getConnection();

    @Override
    public Integer addOrderDetails(OrderDetails orderDetails) {
        Integer row = null;
        try {
            
            java.sql.Date sqlDate = new java.sql.Date(orderDetails.getCreatedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(ADD_ORDER_DETAILS_QUERY);
            pstmt.setInt(1, orderDetails.getProduct().getProductID());
            pstmt.setDouble(2, orderDetails.getSellingPrice());
            pstmt.setInt(3, orderDetails.getQuantity());
            pstmt.setDouble(4, orderDetails.getTotal());
            pstmt.setString(5, orderDetails.getOrder().getOrderCode());
            pstmt.setDate(6, sqlDate);
            pstmt.setInt(7, orderDetails.getCreatedBy());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR IN ADD ACCOUNT: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateOrderDetails(OrderDetails orderDetails) {
        Integer row = null;
        try {
            
            java.sql.Date sqlDate = new java.sql.Date(orderDetails.getModifiedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_ORDER_DETAILS_QUERY);
            pstmt.setInt(1, orderDetails.getProduct().getProductID());
            pstmt.setDouble(2, orderDetails.getSellingPrice());
            pstmt.setInt(3, orderDetails.getQuantity());
            pstmt.setDouble(4, orderDetails.getTotal());
            pstmt.setString(5, orderDetails.getOrder().getOrderCode());
            pstmt.setDate(6, sqlDate);
            pstmt.setInt(7, orderDetails.getModifiedBy());
            pstmt.setInt(8, orderDetails.getOrderDetailID());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR IN ADD ACCOUNT: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteOrderDetails(Integer orderID) {
        Integer row = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(DELETE_ORDER_DETAILS_QUERY);
            pstmt.setInt(1, orderID);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public OrderDetails getOrderDetailsByID(Integer orderID) {
        OrderDetails orderDetails = null;
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ORDER_DETAILS_BY_ID_QUERY);
            pstmt.setInt(1, orderID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                orderDetails = new OrderDetails();
                
                Product product = new Product();
                product.setProductID(rs.getInt(COLUMN_PRODUCT_ID));
                
                Order order = new Order();
                order.setOrderCode(rs.getString(COLUMN_ORDER_CODE));
                
                orderDetails.setOrderDetailID(rs.getInt(COLUMN_ORDER_DETAILS_ID));
                orderDetails.setProduct(product);
                orderDetails.setSellingPrice(rs.getDouble(COLUMN_SELLING_PRICE));
                orderDetails.setQuantity(rs.getInt(COLUMN_QUANTITY));
                orderDetails.setTotal(rs.getDouble(COLUMN_TOTAL));
                orderDetails.setOrder(order);
                orderDetails.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                orderDetails.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                orderDetails.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                orderDetails.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                
            }
            
        } catch (Exception e) {
            System.out.println("ERROR IN ADD ACCOUNT: "+e.getMessage());
            e.printStackTrace();
        }
        
        return orderDetails;
    }

    @Override
    public Integer getOrderDetailsIDByORDERID(Integer orderID) {
        Integer id = null;
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ORDER_DETAILS_ID_BY_ORDER_ID_QUERY);
            pstmt.setInt(1, orderID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                id = rs.getInt(COLUMN_ORDER_DETAILS_ID);
            }
        } catch (Exception e) {
            System.out.println("ERROR IN ADD ACCOUNT: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        List<OrderDetails> allOrderDetails = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_ORDER_DETAILS_QUERY);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            
                OrderDetails orderDetails = new OrderDetails();
                
                Product product = new Product();
                product.setProductID(rs.getInt(COLUMN_PRODUCT_ID));
                
                Order order = new Order();
                order.setOrderCode(rs.getString(COLUMN_ORDER_CODE));
                
                orderDetails.setOrderDetailID(rs.getInt(COLUMN_ORDER_DETAILS_ID));
                orderDetails.setProduct(product);
                orderDetails.setSellingPrice(rs.getDouble(COLUMN_SELLING_PRICE));
                orderDetails.setQuantity(rs.getInt(COLUMN_QUANTITY));
                orderDetails.setTotal(rs.getDouble(COLUMN_TOTAL));
                orderDetails.setOrder(order);
                orderDetails.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                orderDetails.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                orderDetails.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                orderDetails.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                
                allOrderDetails.add(orderDetails);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR IN ADD ACCOUNT: "+e.getMessage());
            e.printStackTrace();
        }
        
        return allOrderDetails;
    }

    @Override
    public List<OrderDetails> getAllDetailsOf(Order order) {
        List<OrderDetails> allOrderDetails = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            String query ="SELECT * FROM order_details WHERE order_code =? AND STATUS=1;";
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_ORDER_DETAILS_OF);
            pstmt.setString(1, order.getOrderCode());
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            
                OrderDetails orderDetails = new OrderDetails();
                
                Product product = new Product();
                product.setProductID(rs.getInt(COLUMN_PRODUCT_ID));
                
                Order order1 = new Order();
                order.setOrderCode(rs.getString(COLUMN_ORDER_CODE));
                
                orderDetails.setOrderDetailID(rs.getInt(COLUMN_ORDER_DETAILS_ID));
                orderDetails.setProduct(product);
                orderDetails.setSellingPrice(rs.getDouble(COLUMN_SELLING_PRICE));
                orderDetails.setQuantity(rs.getInt(COLUMN_QUANTITY));
                orderDetails.setTotal(rs.getDouble(COLUMN_TOTAL));
                orderDetails.setOrder(order1);
                orderDetails.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                orderDetails.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                orderDetails.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                orderDetails.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                
                allOrderDetails.add(orderDetails);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return allOrderDetails;
    }
    
}
