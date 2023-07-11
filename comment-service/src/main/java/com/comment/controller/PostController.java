package com.comment.controller;

import com.comment.entites.Post;
import com.comment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/{userId}")
    public ResponseEntity<Map<String,Object>> createPost(@RequestBody Post post, @PathVariable Long userId) {
        Map<String, Object> map= postService.createPost(post, userId);
        return ResponseEntity.ok().body(map);
    }
}
