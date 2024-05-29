package org.example.spring1.photo;

import org.example.spring1.photo.dto.PhotoDTO;
import org.example.spring1.photo.dto.PhotoRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    PhotoDTO toItemDto(Photo photo);

    Photo toEntity(PhotoRequestDTO item);

    PhotoRequestDTO entityToRequestDTO(Photo photo);
}
