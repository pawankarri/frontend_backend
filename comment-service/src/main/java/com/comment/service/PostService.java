package com.comment.service;

import com.comment.entites.Post;
import com.comment.entites.User;
import com.comment.respository.PostRepository;
import com.comment.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRespository userRespository;
    public Map<String, Object> createPost(Post post, Long userId) {
        Optional<User> optionalUser = userRespository.findById(userId);
        Map<String,Object> map=new HashMap<>();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            post.setUser(user);
            Post post1= postRepository.save(post);
            map.put("message","successfully inserted");
            map.put("result",post1);
            return map;
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }
}
