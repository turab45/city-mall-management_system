/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAO;

import com.shop.model.Order;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public interface OrderDAO {
    public Integer addOrder(Order order);
    public Integer updateOrder(Order order);
    public Integer deleteOrder(Integer orderID);
    public Order getOrderByID(Integer orderID);
    public Integer getOrderIDByCode(String orderCode);
    public List<Order> getAllOrder();
}
