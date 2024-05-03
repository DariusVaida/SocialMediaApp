package org.example.spring1.item;

import org.example.spring1.item.model.Item;
import org.example.spring1.item.model.dto.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<ItemDTO> findAllByName(String name);
}
