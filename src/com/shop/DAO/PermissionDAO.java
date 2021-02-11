/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAO;

import com.shop.model.Permission;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public interface PermissionDAO {
    public Integer addPermission(Permission permission);
    public Integer updatePermission(Permission permission);
    public Integer deletePermission(Integer permissionId);
    public Permission getPermissionById(Integer id);
    public Integer getPermissionIdByName(String name);
    public List<Permission> getAllPermission();
    public List<Permission> getPermissionOf(Integer roleId);
}
