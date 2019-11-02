package com.example.springspotifyclone.service;

import com.example.springspotifyclone.models.UserRole;

public class UserRoleServiceStub implements UserRoleService {

    @Override
    public UserRole createRole(UserRole newRole) {
        // fake standin for sending this along to data layer and back
        UserRole savedUserRole = new UserRole();
        savedUserRole.setName(newRole.getName());

        return savedUserRole;
    }

    @Override
    public UserRole getRole(String roleName) {
        // fake standin for sending this along to data layer and back
        UserRole savedUserRole = new UserRole();
        savedUserRole.setName(roleName);

        return savedUserRole;
    }
}