package com.comment.controller;

import com.comment.entites.Post;
import com.comment.entites.User;
import com.comment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
private UserService userService;
    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> addUser(@RequestBody User user)
    {
        Map<String, Object> map= this.userService.addUser(user);
        return ResponseEntity.ok().body(map);
    }


}
