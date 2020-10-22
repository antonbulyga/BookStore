package com.senla.model.service.api;

import com.senla.model.entity.Role;

import java.util.List;

public interface RoleService {
    Role register(Role role);

    List<Role> getAll();

    Role findByUsername(String name);

    Role findById(Long id);

    void delete(Long id);
}
