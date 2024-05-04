package org.example.spring1.post;

import org.example.spring1.post.model.Post;
import org.example.spring1.post.model.dto.PostDTO;
import org.example.spring1.post.model.dto.PostRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDTO toItemDto(Post post);

    Post toEntity(PostRequestDTO item);

    PostRequestDTO entityToRequestDTO(Post post);
}