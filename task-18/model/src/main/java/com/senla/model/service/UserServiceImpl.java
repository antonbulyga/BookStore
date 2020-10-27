package com.senla.model.service;

import com.senla.model.entity.Role;
import com.senla.model.entity.Status;
import com.senla.model.entity.User;
import com.senla.model.repository.api.RoleRepository;
import com.senla.model.repository.api.UserRepository;
import com.senla.model.service.api.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User register(User user) {
        List<Role> roles = user.getRoles();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.update(user);
        logger.info("In register - user: " + registeredUser + "successfully registered");
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        logger.info("IN getAll - " + result.size() + "users found");
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        logger.info("In findByUsername - user:" + result + "found by username: " + username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id);
        if (result == null) {
            logger.warn("In findById - no user found by id:" + id);
            return null;
        }

        logger.info("In findById - user: {} found by id:" + result);
        return result;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        logger.info("In delete - user with id: {} successfully deleted");
    }

    @Override
    public User update(User user){
        userRepository.update(user);
        return user;
    }

    public User passwordCoder(User user){
        String pass = user.getPassword();
        String cryptPass = passwordEncoder.encode(pass);
        user.setPassword(cryptPass);
        return user;
    }
}
