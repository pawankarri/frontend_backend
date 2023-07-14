package com.comment.service;

import com.comment.entites.Like;
import com.comment.respository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;


    public  Map<String,Object> findCount(long postId)
    {
        Map<String,Object> map=new HashMap<>();
        long count=likeRepository.findCountByPostId(postId);
        map.put("status", HttpStatus.OK.value());
        map.put("message","successfully inserted");
        map.put("result",count);
        return map;
    }
}
