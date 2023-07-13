package com.comment.controller;

import com.comment.entites.Post;
import com.comment.entites.User;
import com.comment.service.PostService;
import com.comment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<Post> createPost(@RequestParam String title,@RequestParam String description,
                                           @RequestParam("imageFile") MultipartFile imageFile, @RequestParam("userId") Long userId)
            throws IOException
    {
        User user = userService.getUserById(userId);
        Post post = postService.createPost(title, description, imageFile, user);
        System.out.println(imageFile.getBytes());
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }


    @GetMapping("id/{id}")
    public Resource getAllPosts(@PathVariable("id") long id) throws MalformedURLException {
        return this.postService.getAllPosts(id);
    }



    @PostMapping("/{postId}/like")
    public void likePost(@PathVariable Long postId) {
        postService.likePost(postId);
    }

}
