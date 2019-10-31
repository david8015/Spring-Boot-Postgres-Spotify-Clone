package com.example.springspotifyclone.service;

import com.example.springspotifyclone.models.UserRole;
import com.example.springspotifyclone.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRole createRole(UserRole newRole) {
        return userRoleRepository.save(newRole);
    }

    @Override
    public UserRole getRole(String roleName) {
        return userRoleRepository.findByName(roleName);
    }
}