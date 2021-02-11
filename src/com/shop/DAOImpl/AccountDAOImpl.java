/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import com.shop.DAO.AccountDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Account;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class AccountDAOImpl implements AccountDAO{
    
    // table and column names
    private static final String TABLE_ACCOUNT = "accounts";
    private static final String COLUMN_ACCOUNT_ID = "account_id";
    private static final String COLUMN_ACCOUNT_NAME = "account_title";
    private static final String COLUMN_BANK_NAME = "bank_name";
    private static final String COLUMN_ACCOUNT_NUMBER = "account_number";
    private static final String COLUMN_CURRENT_BALANCE = "current_balance";
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_STATUS = "status";
    
    
    //queries
    private static final String ADD_ACCOUNT = "insert into "+TABLE_ACCOUNT+"("+COLUMN_ACCOUNT_NAME+","+COLUMN_BANK_NAME+","+COLUMN_ACCOUNT_NUMBER+","+COLUMN_CURRENT_BALANCE+","+COLUMN_REMARKS+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values (?,?,?,?,?,?,?,1)";
    private static final String UPDATE_ACCOUNT = "update "+TABLE_ACCOUNT+" set "+COLUMN_ACCOUNT_NAME+"=?, "+COLUMN_BANK_NAME+"=?,"+COLUMN_CURRENT_BALANCE+"=?,"+COLUMN_REMARKS+"=?,"+COLUMN_MODIFIED_DATE+"=?,"+COLUMN_MODIFIED_BY+"=? where "+COLUMN_ACCOUNT_ID+"=?";
    private static final String DELETE_ACCOUNT = "update "+TABLE_ACCOUNT+" set "+COLUMN_STATUS+" =0 where "+COLUMN_ACCOUNT_ID+" =?";
    private static final String GET_ACCOUNT_BY_ID = "select * from "+TABLE_ACCOUNT+" where "+COLUMN_ACCOUNT_ID+" =?";
    private static final String GET_ACCOUNT_ID_BY_NAME = "select "+COLUMN_ACCOUNT_ID+" from "+TABLE_ACCOUNT+" where "+COLUMN_ACCOUNT_NAME+" =? ";
    private static final String GET_ALL_ACCOUNT = "select * from "+TABLE_ACCOUNT+" where "+COLUMN_STATUS+" =1";
    private static final String CHECK_ACCOUNT = "select * from "+TABLE_ACCOUNT+" where "+COLUMN_ACCOUNT_NUMBER+" =?";
    private static final String CHECK_ACCOUNT_ALREADY_EXIST = "select "+COLUMN_ACCOUNT_NAME+" from "+TABLE_ACCOUNT+" where "+COLUMN_ACCOUNT_NAME+" =?";
    private static final String DEPOSIT_AMOUNT = "update "+TABLE_ACCOUNT+" set "+COLUMN_CURRENT_BALANCE+" = "+COLUMN_CURRENT_BALANCE+" + ? where "+COLUMN_ACCOUNT_ID+" =?";
    private static final String WITHDRAW_AMOUNT = "update "+TABLE_ACCOUNT+" set "+COLUMN_CURRENT_BALANCE+" = "+COLUMN_CURRENT_BALANCE+" - ? where "+COLUMN_ACCOUNT_ID+" =?";

    Connection conn = DBConnection.getConnection();

    @Override
    public Integer addAccount(Account account) {
        Integer row = null;
        try {
            
            java.sql.Date sqlDate = new java.sql.Date(account.getCreatedDate().getTime());
            
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(ADD_ACCOUNT);
            pstmt.setString(1, account.getAccountName());
            pstmt.setString(2, account.getBankName());
            pstmt.setString(3, account.getAccountNumber());
            pstmt.setDouble(4, account.getCurrentBalance());
            pstmt.setString(5, account.getRemarks());
            pstmt.setDate(6, sqlDate);
            pstmt.setInt(7, account.getCreatedBy());
            
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR IN ADD ACCOUNT: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updateAccount(Account account) {
        Integer row = null;
        try {
            
            java.sql.Date sqlDate = new java.sql.Date(account.getModifiedDate().getTime());
            
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(UPDATE_ACCOUNT);
            pstmt.setString(1, account.getAccountName());
            pstmt.setString(2, account.getBankName());
            pstmt.setDouble(3, account.getCurrentBalance());
            pstmt.setString(4, account.getRemarks());
            pstmt.setDate(5, sqlDate);
            pstmt.setInt(6, account.getModifiedBy());
            pstmt.setInt(7, account.getAccountID());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR IN UPDATE ACCOUNT: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteAccount(Integer accountID) {
        Integer row = null;
        try {
            
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(DELETE_ACCOUNT);
            pstmt.setInt(1, accountID);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            
            System.out.println("ERROR IN DELETE ACCOUNT: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Account getAccountByID(Integer accountID) {
        Account account = null;
        ResultSet rs = null;
        
        try {
           
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(GET_ACCOUNT_BY_ID);
            pstmt.setInt(1, accountID);
            
            rs = (ResultSet) pstmt.executeQuery();
            
            if(rs.next()){
            
                account = new Account();
                
                account.setAccountID(rs.getInt(COLUMN_ACCOUNT_ID));
                account.setAccountName(rs.getString(COLUMN_ACCOUNT_NAME));
                account.setBankName(rs.getString(COLUMN_BANK_NAME));
                account.setAccountNumber(rs.getString(COLUMN_ACCOUNT_NUMBER));
                account.setCurrentBalance(rs.getDouble(COLUMN_CURRENT_BALANCE));
                account.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                account.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                account.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                account.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                account.setRemarks(rs.getString(COLUMN_REMARKS));
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return account;
    }

    @Override
    public Integer getAccountIDByName(String accountName) {
        Integer id = null;
        ResultSet rs = null;
        try {
                
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(GET_ACCOUNT_ID_BY_NAME);
            pstmt.setString(1, accountName);
            
            rs = (ResultSet) pstmt.executeQuery();
            
            if(rs.next()){
            
                id = rs.getInt(COLUMN_ACCOUNT_ID);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Account> getAllAccount() {
        
        List<Account> allAccounts = new ArrayList<>();
        ResultSet rs = null;
        
        try {
           
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(GET_ALL_ACCOUNT);
            
            rs = (ResultSet) pstmt.executeQuery();
            
            while(rs.next()){
                
                Account account = new Account();
            
                account.setAccountID(rs.getInt(COLUMN_ACCOUNT_ID));
                account.setAccountName(rs.getString(COLUMN_ACCOUNT_NAME));
                account.setBankName(rs.getString(COLUMN_BANK_NAME));
                account.setAccountNumber(rs.getString(COLUMN_ACCOUNT_NUMBER));
                account.setCurrentBalance(rs.getDouble(COLUMN_CURRENT_BALANCE));
                account.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                account.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                account.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                account.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                account.setRemarks(rs.getString(COLUMN_REMARKS));
                
                allAccounts.add(account);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return allAccounts;
    }

    @Override
    public boolean checkAccountNumber(String account) {
        boolean result = false;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(CHECK_ACCOUNT);
            pstmt.setString(1, account);
            
            rs = (ResultSet) pstmt.executeQuery();
            
            if(rs.next()){
            
                result = true;
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean checkAccount(String accountName) {
        boolean result = false;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(CHECK_ACCOUNT_ALREADY_EXIST);
            pstmt.setString(1, accountName);
            
            rs = (ResultSet) pstmt.executeQuery();
            
            if(rs.next()){
            
                result = true;
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer depositAmount(Account account,Double amount) {
        Integer row = null;
        try {
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(DEPOSIT_AMOUNT);
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, account.getAccountID());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer withdrawAmount(Account account, Double amount) {
        Integer row = null;
        try {
            
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(WITHDRAW_AMOUNT);
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, account.getAccountID());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }
    
}
