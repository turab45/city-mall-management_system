/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAO;

import com.shop.model.Account;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public interface AccountDAO {
    public Integer addAccount(Account account);
    public Integer updateAccount(Account account);
    public Integer deleteAccount(Integer accountID);
    public Account getAccountByID(Integer accountID);
    public Integer getAccountIDByName(String accountName);
    public List<Account> getAllAccount();
    public boolean checkAccountNumber(String account);
    public boolean checkAccount(String accountName);
    public Integer depositAmount(Account account,Double amount);
    public Integer withdrawAmount(Account account,Double amount);
}
