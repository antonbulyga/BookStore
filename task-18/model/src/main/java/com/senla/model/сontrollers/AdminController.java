package com.senla.model.сontrollers;

import com.senla.model.dto.AdminUserDto;
import com.senla.model.dto.UserDto;
import com.senla.model.entity.User;
import com.senla.model.service.UserServiceImpl;
import com.senla.model.service.api.UserService;
import com.senla.model.utils.DtoConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/")
public class AdminController {

    private final UserService userService;
    private final DtoConverter dtoConverter;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    public AdminController(UserService userService, DtoConverter dtoConverter, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.dtoConverter = dtoConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = dtoConverter.fromUserToAdmin(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
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
    @Secured("ROLE_ADMIN")
    @DeleteMapping("delete")
    public AdminUserDto deleteBook(@RequestBody AdminUserDto adminUserDto) {
        User user = dtoConverter.adminToUser(adminUserDto);
        userService.deleteById(user.getId());
        return adminUserDto;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("register")
    public AdminUserDto register(@RequestBody AdminUserDto adminUserDto) {
        User user = dtoConverter.adminToUser(adminUserDto);
        userService.register(user);
        return adminUserDto;
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("update")
    public UserDto update(UserDto userDto){
        User user = dtoConverter.toUser(userDto);
        userService.update(user);
        return userDto;
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("{id}/password")
    public AdminUserDto changeUserPassword(@PathVariable long id, @PathVariable String password) {
        User user = userService.findById(id);
        String cryptPassword = passwordEncoder.encode(password);
        user.setPassword(cryptPassword);
        AdminUserDto adminUserDto = dtoConverter.fromUserToAdmin(user);
        return adminUserDto;
    }

}
