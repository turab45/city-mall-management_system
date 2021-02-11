/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAO;

import com.shop.model.User;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public interface UserDAO {
    public Integer addUser(User role);
    public Integer updateUser(User role);
    public Integer deleteUser(Integer roleId);
    public User getUserById(Integer id);
    public Integer getUserIdByName(String name);
    public List<User> getAllUser();
    
}
