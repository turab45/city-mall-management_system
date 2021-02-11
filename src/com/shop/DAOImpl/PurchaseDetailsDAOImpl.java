/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.mysql.jdbc.Connection;
import com.shop.DAO.PurchaseDetailsDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Product;
import com.shop.model.Purchase;
import com.shop.model.PurchaseDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class PurchaseDetailsDAOImpl implements PurchaseDetailsDAO{
    
    // table and column names
    private static final String TABLE_PURCHASE_DETAILS = "purchase_details";
    private static final String COLUMN_PURCHASE_DETAILS_ID = "purchase_details_id";
    private static final String COLUMN_PURCHASE_CODE = "purchase_code";
    private static final String COLUMN_TOTAL = "total";
    private static final String COLUMN_PRODUCT_ID = "product_id";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PURCHASE_PRICE = "purchase_price";
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_STATUS = "status";
    
    
    //queries
    private static final String ADD_PURCHASE_DETAILS = "insert into "+TABLE_PURCHASE_DETAILS+"("+COLUMN_PURCHASE_CODE+","+COLUMN_TOTAL+","+COLUMN_PRODUCT_ID+","+COLUMN_QUANTITY+","+COLUMN_PURCHASE_PRICE+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values (?,?,?,?,?,?,?,1)";
    private static final String UPDATE_PURCHASE_DETAILS = "update "+TABLE_PURCHASE_DETAILS+" set "+COLUMN_PURCHASE_CODE+"=?, "+COLUMN_TOTAL+"=?,"+COLUMN_PRODUCT_ID+"=?,"+COLUMN_QUANTITY+"=?,"+COLUMN_PURCHASE_PRICE+"=?,"+COLUMN_REMARKS+"=?,"+COLUMN_MODIFIED_DATE+"=?,"+COLUMN_MODIFIED_BY+"=? where "+COLUMN_PURCHASE_DETAILS_ID+"=?";
    private static final String DELETE_PURCHASE_DETAILS = "update "+TABLE_PURCHASE_DETAILS+" set "+COLUMN_STATUS+"=0 where "+COLUMN_PURCHASE_CODE+" =?";
    private static final String GET_PURCHASE_DETAILS_BY_ID = "select * from "+TABLE_PURCHASE_DETAILS+" where "+COLUMN_PURCHASE_CODE+" =?";
    private static final String GET_PURCHASE_DETAILS_ID_BY_PURCHASE_ID = "select "+COLUMN_PURCHASE_DETAILS_ID+" from "+TABLE_PURCHASE_DETAILS+" where "+COLUMN_PURCHASE_CODE+" =? ";
    private static final String GET_ALL_PURCHASE_DETAILS = "selectt * from "+TABLE_PURCHASE_DETAILS;
    
    
    // Connection
    Connection conn = DBConnection.getConnection();

    @Override
    public Integer addPurchaseDetails(PurchaseDetails purchaseDetails) {
        Integer row = null;
        try {
            
            java.sql.Date createdDate = new java.sql.Date(purchaseDetails.getCreatedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(ADD_PURCHASE_DETAILS);
            pstmt.setString(1, purchaseDetails.getPurchase().getPurchaseCode());
            pstmt.setDouble(2, purchaseDetails.getTotal());
            pstmt.setInt(3, purchaseDetails.getProduct().getProductID());
            pstmt.setInt(4, purchaseDetails.getQuantity());
            pstmt.setDouble(5, purchaseDetails.getPurchasePrice());
            pstmt.setDate(6, createdDate);
            pstmt.setInt(7, purchaseDetails.getCreatedBy());
            
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updatePurchaseDetails(PurchaseDetails purchaseDetails) {
        Integer row = null;
        try {
            
            java.sql.Date createdDate = new java.sql.Date(purchaseDetails.getCreatedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_PURCHASE_DETAILS);
            pstmt.setInt(1, purchaseDetails.getPurchase().getPurchaseID());
            pstmt.setDouble(2, purchaseDetails.getTotal());
            pstmt.setInt(3, purchaseDetails.getProduct().getProductID());
            pstmt.setInt(4, purchaseDetails.getQuantity());
            pstmt.setDouble(5, purchaseDetails.getPurchasePrice());
            pstmt.setDate(6, createdDate);
            pstmt.setInt(7, purchaseDetails.getCreatedBy());
            pstmt.setInt(8, purchaseDetails.getPurchaseDetailsID());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deletePurchaseDetails(Integer purchaseDetailsID) {
        Integer row = null;
        try {
            
            PreparedStatement pstmt = conn.prepareStatement(DELETE_PURCHASE_DETAILS);
            pstmt.setInt(1, purchaseDetailsID);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public PurchaseDetails getPurchaseDetailsByID(Integer purchaseDetailsID) {
        PurchaseDetails purchaseDetails = null;
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_PURCHASE_DETAILS_BY_ID);
            pstmt.setInt(1, purchaseDetailsID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                purchaseDetails = new PurchaseDetails();
                
                Purchase purchase = new Purchase();
                purchase.setPurchaseID(rs.getInt(COLUMN_PURCHASE_CODE));
                
                Product product = new Product();
                product.setProductID(rs.getInt(COLUMN_PRODUCT_ID));
                
                purchaseDetails.setPurchaseDetailsID(rs.getInt(COLUMN_PURCHASE_DETAILS_ID));
                purchaseDetails.setTotal(rs.getDouble(COLUMN_TOTAL));
                purchaseDetails.setProduct(product);
                purchaseDetails.setQuantity(rs.getInt(COLUMN_QUANTITY));
                purchaseDetails.setPurchasePrice(rs.getDouble(COLUMN_PURCHASE_PRICE));
                purchaseDetails.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                purchaseDetails.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                purchaseDetails.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                purchaseDetails.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                purchaseDetails.setRemarks(rs.getString(COLUMN_REMARKS));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return purchaseDetails;
    }

    @Override
    public Integer getPurchaseDetailsIDByPurchaseID(Integer purchaseDetailsID) {
        Integer id = null;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_PURCHASE_DETAILS_ID_BY_PURCHASE_ID);
            pstmt.setInt(1, purchaseDetailsID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                id = rs.getInt(COLUMN_PURCHASE_DETAILS_ID);
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<PurchaseDetails> getAllVendor() {
        List<PurchaseDetails> allPurchaseDetails = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_PURCHASE_DETAILS_BY_ID);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            
                PurchaseDetails purchaseDetails = new PurchaseDetails();
                
                Purchase purchase = new Purchase();
                purchase.setPurchaseID(rs.getInt(COLUMN_PURCHASE_CODE));
                
                Product product = new Product();
                product.setProductID(rs.getInt(COLUMN_PRODUCT_ID));
                
                purchaseDetails.setPurchaseDetailsID(rs.getInt(COLUMN_PURCHASE_DETAILS_ID));
                purchaseDetails.setTotal(rs.getDouble(COLUMN_TOTAL));
                purchaseDetails.setProduct(product);
                purchaseDetails.setQuantity(rs.getInt(COLUMN_QUANTITY));
                purchaseDetails.setPurchasePrice(rs.getDouble(COLUMN_PURCHASE_PRICE));
                purchaseDetails.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                purchaseDetails.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                purchaseDetails.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                purchaseDetails.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                purchaseDetails.setRemarks(rs.getString(COLUMN_REMARKS));
                
                allPurchaseDetails.add(purchaseDetails);
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return allPurchaseDetails;
    }

    @Override
    public List<PurchaseDetails> getAllDetailsOf(Purchase purchase) {
        
        List<PurchaseDetails> allPurchaseDetails = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            String query ="SELECT * FROM purchase_details WHERE purchase_code =? AND STATUS=1;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, purchase.getPurchaseCode());
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            
                PurchaseDetails allDetails = new PurchaseDetails();
                
                Product product = new Product();
                product.setProductID(rs.getInt(COLUMN_PRODUCT_ID));
                
                Purchase purchase1 = new Purchase();
                purchase1.setPurchaseCode(rs.getString("purchase_code"));
                
                allDetails.setPurchase(purchase);
                allDetails.setProduct(product);
                allDetails.setPurchasePrice(rs.getDouble(COLUMN_PURCHASE_PRICE));
                allDetails.setQuantity(rs.getInt(COLUMN_QUANTITY));
                allDetails.setTotal(rs.getDouble(COLUMN_TOTAL));
                allDetails.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                allDetails.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                allDetails.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                allDetails.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                
                allPurchaseDetails.add(allDetails);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return allPurchaseDetails;
    
    }

    

    
    
}
