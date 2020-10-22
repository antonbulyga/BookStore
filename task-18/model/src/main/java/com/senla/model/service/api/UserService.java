package com.senla.model.service.api;

import com.senla.model.entity.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void deleteById(Long id);

    User update(User user);
}
