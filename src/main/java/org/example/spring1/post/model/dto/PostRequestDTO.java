package org.example.spring1.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO {
    private Long id;
    private String name;
    private String description;
    private Long photoId;
    private Long userId;

    public PostRequestDTO(Long id) {
        this.photoId = id;
    }
}