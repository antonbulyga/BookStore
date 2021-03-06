package com.senla.model.сontrollers;

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
@RequestMapping(value = "/users/")
public class UserController {
    private final UserService userService;
    private final DtoConverter dtoConverter;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    public UserController(UserService userService, DtoConverter dtoConverter, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.dtoConverter = dtoConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
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
    @Secured("ROLE_ADMIN")
    @DeleteMapping("delete")
    public UserDto deleteBook(@RequestBody UserDto userDto) {
        User user = dtoConverter.toUser(userDto);
        userService.deleteById(user.getId());
        return userDto;
    }

    @PostMapping("register")
    public User register(@RequestBody UserDto userDto) {
        User user = dtoConverter.toUser(userDto);
        userService.register(user);
        return user;
    }

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = dtoConverter.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @PutMapping("update")
    public UserDto update(UserDto userDto){
        User user = dtoConverter.toUser(userDto);
        User newUser = userService.passwordCoder(user);
        userService.update(newUser);
        return userDto;
    }

}
