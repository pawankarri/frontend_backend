package com.comment.service;

import com.comment.entites.Like;
import com.comment.entites.Post;
import com.comment.entites.User;
import com.comment.exception.UserAlreadyLikedException;
import com.comment.respository.LikeRepository;
import com.comment.respository.PostRepository;
import com.comment.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.web.multipart.MultipartFile;



@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRespository userRespository;

    @Autowired
    private LikeRepository likeRepository;

        @Value("${files.storage}")
        private String imageUploadPath;

        public Post createPost(String title, String description, MultipartFile imageFile, User user) throws IOException {
            byte[] imageBytes = imageFile.getBytes();

            String imageName = imageFile.getOriginalFilename();
            String imagePath = imageUploadPath + imageName;

            Path destinationPath = Path.of(imagePath);
            Files.copy(imageFile.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            Post post = new Post();
            post.setTitle(title);
            post.setDescription(description);
            post.setImagePath(imagePath);

            post.setUser(user);

            return postRepository.save(post);
        }





    public Resource getAllPosts(long id) throws MalformedURLException {
        Post post = postRepository.findById(id).orElseThrow(()->new NullPointerException("null"));
        try {
            Path file = Paths.get(post.getImagePath());
            Resource resource =  new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
               // System.out.println("service"+resource);
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


        public void likePost(Long postId, Long userId) throws UserAlreadyLikedException {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new RuntimeException("Post not found"));

            // Check if the user has already liked the post
            Like existingLike = likeRepository.findByPostIdAndUserId(postId, userId);
            if (existingLike != null) {
                throw new UserAlreadyLikedException("You have already liked this post");
            }

            // Save the like
            Like like = new Like();
            like.setPostId(post.getPostId());
            like.setUserId(userId);
            likeRepository.save(like);

        }

        // ...


    //getAllPosts

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Resource> getAllPostImages() throws IOException {
        List<Post> posts = getAllPosts();

        List<Resource> images = new ArrayList<>();

        for (Post post : posts) {
            Path imagePath = Paths.get(post.getImagePath());
            System.out.println(imagePath);
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                images.add(resource);
            } else {
                throw new RuntimeException("Could not read the file: " + imagePath);
            }
        }
        System.out.println("post"+images);
        return images;
    }
}













