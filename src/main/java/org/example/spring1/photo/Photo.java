package org.example.spring1.photo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "photos")
@Getter
@Setter
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8192)
    private String name;

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
