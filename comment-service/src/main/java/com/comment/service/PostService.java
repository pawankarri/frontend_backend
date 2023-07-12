package com.comment.service;

import com.comment.entites.Like;
import com.comment.entites.Post;
import com.comment.entites.User;
import com.comment.respository.LikeRepository;
import com.comment.respository.PostRepository;
import com.comment.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

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





    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }



    public void likePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Increment the likes count for the post
       // post.setLikes(post.getLikes() + 1);

        // Save the updated post
       // postRepository.save(post);

        // Create a new Like entity and store the post ID and user ID
        Like like = new Like();
        like.setPostId(postId);
        like.setUserId(userId);

        // Save the like entity
        likeRepository.save(like);
    }
    }


