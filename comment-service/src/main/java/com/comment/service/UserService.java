package com.comment.service;

import com.comment.entites.User;
import com.comment.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRespository userRespository;

    public Map<String, Object> addUser(User user)
    {
        Map<String,Object> map=new HashMap<>();
        User user1= userRespository.save(user);
        map.put("message","successfully inserted");
        map.put("result",user1);
        return map;
    }


}
