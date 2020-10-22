package com.senla.model.сontrollers;

import com.senla.model.dto.AdminUserDto;
import com.senla.model.dto.UserDto;
import com.senla.model.entity.Role;
import com.senla.model.entity.Status;
import com.senla.model.entity.User;
import com.senla.model.repository.HibernateImpl.RoleHibernateRepository;
import com.senla.model.service.UserServiceImpl;
import com.senla.model.service.api.UserService;
import com.senla.model.utils.DtoConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/")
public class AdminController {

    private final UserService userService;
    private final DtoConverter dtoConverter;
    private final RoleHibernateRepository roleHibernateRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    public AdminController(UserService userService, DtoConverter dtoConverter, RoleHibernateRepository roleHibernateRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.dtoConverter = dtoConverter;
        this.roleHibernateRepository = roleHibernateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = dtoConverter.fromUserToAdmin(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<UserDto>> getAll(){
        List<User> users = userService.getAll();
        List<UserDto> userDtoList = new ArrayList<>();
        if(users == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (int i = 0; i < users.size(); i++) {
            UserDto result = dtoConverter.fromUser(users.get(i));
            userDtoList.add(result);
        }
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }
    @DeleteMapping("delete")
    public UserDto deleteBook(@RequestBody UserDto userDto) {
        User user = dtoConverter.toUser(userDto);
        userService.deleteById(user.getId());
        return userDto;
    }

    @PostMapping("register")
    public User register(@RequestBody UserDto userDto) {
        Role roleUser = roleHibernateRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        User user = new User();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userService.update(user);

        logger.info("In register - user: " + registeredUser + "successfully registered");

        return registeredUser;
    }

}
