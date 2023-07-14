package com.comment.respository;

import com.comment.entites.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeRepository extends JpaRepository<Like,Long> {

    @Query(value = "SELECT COUNT(*) FROM post_like WHERE post_Id = ?1", nativeQuery = true)
    long findCountByPostId(long postId);

}
