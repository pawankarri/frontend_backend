package com.comment.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    private String title;
    private String description;
    @OneToMany(mappedBy = "likeId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Like> like;
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnore
    private  User user;
}
