package org.example.spring1.photo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.spring1.post.model.Post;

@Entity
@Table(name = "photos")
@Getter
@Setter
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Post post;

    @Column(length = 8192)
    private String name;

    @Column(length = 128)
    private String fileType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;

    public Photo(String fileName, String contentType, byte[] bytes) {
        this.name = fileName;
        this.data = bytes;
    }

    public Photo() {

    }
}
