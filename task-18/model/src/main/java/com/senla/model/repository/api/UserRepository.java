package com.senla.model.repository.api;

import com.senla.model.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findByUsername(String username);

    User findById(Long id);

    void deleteById(Long id);

    User update(User user);
}
