package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.ResponseWrapper;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RolesAllowed("Admin")
    @GetMapping
    public ResponseEntity<ResponseWrapper> getUsers(){
        List<UserDTO> userDTOList = userService.listAllUsers();
        return ResponseEntity.ok(new ResponseWrapper("Users are successfully retrieved", userDTOList, HttpStatus.OK));
    }

    @RolesAllowed("Admin")
    @GetMapping("/{userName}")
    public ResponseEntity<ResponseWrapper> getUserByUserName(@PathVariable("userName") String userName){
        return ResponseEntity.ok(new ResponseWrapper("User is successfully retrieved", userService.findByUserName(userName), HttpStatus.OK));
    }

    @RolesAllowed("Admin")
    @PostMapping
    public ResponseEntity<ResponseWrapper> createUser(@RequestBody UserDTO user){
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("User successfully created", HttpStatus.CREATED));
    }

    @RolesAllowed("Admin")
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateUser(@RequestBody UserDTO user){
        userService.update(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("User successfully updated", HttpStatus.CREATED));
    }

    @RolesAllowed("Admin")
    @DeleteMapping("/{userName}")
    public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable String userName){
        userService.delete(userName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseWrapper("User successfully deleted", HttpStatus.NO_CONTENT));
    }

}
