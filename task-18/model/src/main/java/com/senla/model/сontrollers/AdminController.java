package com.senla.model.сontrollers;

import com.senla.model.dto.AdminUserDto;
import com.senla.model.dto.UserDto;
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
    public ResponseEntity<List<AdminUserDto>> getAll(){
        List<User> users = userService.getAll();
        List<AdminUserDto> userDtoList = new ArrayList<>();
        if(users == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (int i = 0; i < users.size(); i++) {
            AdminUserDto result = dtoConverter.fromUserToAdmin(users.get(i));
            userDtoList.add(result);
        }
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }
    @DeleteMapping("delete")
    public AdminUserDto deleteBook(@RequestBody AdminUserDto adminUserDto) {
        User user = dtoConverter.adminToUser(adminUserDto);
        userService.deleteById(user.getId());
        return adminUserDto;
    }

    @PostMapping("register")
    public AdminUserDto register(@RequestBody AdminUserDto adminUserDto) {
        User user = dtoConverter.adminToUser(adminUserDto);
        userService.register(user);
        return adminUserDto;
    }

    public UserDto update(UserDto userDto){
        User user = dtoConverter.toUser(userDto);
        userService.update(user);
        return userDto;
    }

}
