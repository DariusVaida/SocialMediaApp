package org.example.spring1.item;

import lombok.RequiredArgsConstructor;
import org.example.spring1.item.model.dto.ItemDTO;
import org.example.spring1.item.model.dto.ItemRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;
  private final ItemMapper itemMapper;

  public List<ItemDTO> findAll() {
    return itemRepository.findAll().stream().map(itemMapper::toItemDto).toList();
  }

  public ResponseEntity<?> get(Long id) {
    return itemRepository.findById(id)
        .map(itemMapper::toItemDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  public ItemDTO create(ItemRequestDTO dto) {
    return itemMapper.toItemDto(itemRepository.save(itemMapper.toEntity(dto)));
  }

  public void delete(Long id) {

    itemRepository.deleteById(id);

  }

  public List<ItemDTO> findAllFiltered(String name) {
    return itemRepository.findAllByName(name);
  }

  public ItemDTO update(Long id, ItemRequestDTO dto) {
    return itemRepository.findById(id)
        .map(item -> {
          item.setName(dto.getName());
          item.setDescription(dto.getDescription());
          return itemMapper.toItemDto(itemRepository.save(item));
        })
        .orElse(null);
  }

  public ItemDTO changeName(Long id, String newName) {
    return itemRepository.findById(id)
        .map(item -> {
          item.setName(newName);
          return itemMapper.toItemDto(itemRepository.save(item));
        })
        .orElse(null);
  }

  public void deleteMultiple(List<Long> ids) {
    ids.forEach(itemRepository::deleteById);
  }

  public ItemDTO changeDescription(Long id, String newDescription) {
    return itemRepository.findById(id)
        .map(item -> {
          item.setDescription(newDescription);
          return itemMapper.toItemDto(itemRepository.save(item));
        })
        .orElse(null);
  }
}