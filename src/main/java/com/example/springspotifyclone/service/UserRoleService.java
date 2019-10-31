package com.example.springspotifyclone.service;

import com.example.springspotifyclone.models.UserRole;

public interface UserRoleService {

    public UserRole createRole(UserRole newRole);

    public UserRole getRole(String roleName);
}