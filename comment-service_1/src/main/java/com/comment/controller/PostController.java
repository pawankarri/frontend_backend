package com.comment.controller;

import com.comment.entites.Post;
import com.comment.entites.User;
import com.comment.exception.UserAlreadyLikedException;
import com.comment.service.PostService;
import com.comment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;


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
        System.out.println("controller"+this.postService.getAllPosts(id));
        return this.postService.getAllPosts(id);
    }



    @PostMapping("/like/{postId}/{userId}")
    public void likePost(@PathVariable long postId, @PathVariable long userId)  {
        postService.likePost(postId, userId);
    }





    //getAllPosts

    @GetMapping("/posts")
    public ResponseEntity<List<Resource>> getAllPostImages() {
        try {
            List<Resource> images = postService.getAllPostImages();
            System.out.println("controller"+images);
            return ResponseEntity.ok(images);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
