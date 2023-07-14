package com.comment.controller;

import com.comment.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/like")
@CrossOrigin("*")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @GetMapping("/count/{postId}")
    public ResponseEntity<Map<String,Object>> likeCount(@PathVariable long postId)
    {
        Map<String, Object> map=this.likeService.findCount(postId);
        return ResponseEntity.ok().body(map);
    }
}
