/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAO;

import com.shop.model.Order;
import com.shop.model.OrderDetails;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public interface OrderDetailsDAO {
    public Integer addOrderDetails(OrderDetails order);
    public Integer updateOrderDetails(OrderDetails order);
    public Integer deleteOrderDetails(Integer orderID);
    public OrderDetails getOrderDetailsByID(Integer orderID);
    public Integer getOrderDetailsIDByORDERID(Integer orderID);
    public List<OrderDetails> getAllOrderDetails();
    public List<OrderDetails> getAllDetailsOf(Order order);
}
