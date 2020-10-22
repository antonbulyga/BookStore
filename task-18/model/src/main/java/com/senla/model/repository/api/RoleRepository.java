package com.senla.model.repository.api;

import com.senla.model.entity.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> getAll();

    Role findByName(String name);

    Role findById(Long id);

    void delete(Long id);

    Role update(Role role);
}
