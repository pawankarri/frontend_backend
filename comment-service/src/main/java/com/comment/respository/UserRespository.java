package com.comment.respository;

import com.comment.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Long> {
}
