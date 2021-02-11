/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAO;

import com.shop.model.Vendor;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public interface VendorDAO {
    public Integer addVendor(Vendor vendor);
    public Integer updateVendor(Vendor vendor);
    public Integer deleteVendor(Integer vendorID);
    public Vendor getVendorByID(Integer vendorID);
    public Integer getVendorIDByName(String vendorName);
    public List<Vendor> getAllVendor();
}
