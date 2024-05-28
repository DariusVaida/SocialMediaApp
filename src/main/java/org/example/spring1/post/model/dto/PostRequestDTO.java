package org.example.spring1.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.spring1.user.model.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO {
    private Long id;
    private String name;
    private String description;
    private Long userId;
}