package org.example.spring1.post.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.spring1.photo.Photo;
import org.example.spring1.user.model.User;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 512)
    private String description;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
