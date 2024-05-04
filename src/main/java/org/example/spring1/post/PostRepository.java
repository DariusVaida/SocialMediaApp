package org.example.spring1.post;

import org.example.spring1.post.model.Post;
import org.example.spring1.post.model.dto.PostDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<PostDTO> findAllByName(String name);
}
