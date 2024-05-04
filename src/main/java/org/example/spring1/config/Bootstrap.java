package org.example.spring1.config;

import lombok.RequiredArgsConstructor;
import org.example.spring1.item.ItemRepository;
import org.example.spring1.item.model.Item;
import org.example.spring1.user.RoleRepository;
import org.example.spring1.user.UserRepository;
import org.example.spring1.user.model.ERole;
import org.example.spring1.user.model.Role;
import org.example.spring1.user.model.User;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class Bootstrap {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void bootstrapData() {
        for (ERole value : ERole.values()) {
            if (roleRepository.findByName(value).isEmpty()) {
                roleRepository.save(
                        Role.builder().name(value).build()
                );
            }
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void bootstrapUsers() {

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(ERole.ADMIN).orElseThrow());
        if (userRepository.findByUsername("admin").isEmpty()) {
            userRepository.save(
                    User.builder()
                            .id(1L)
                            .username("admin")
                            .password("admin")
                            .email("admin@admin.com")
                            .roles(roles)
                            .build()
            );
        }
        if (userRepository.findByUsername("user").isEmpty()) {

            Set<Role> roles2 = new HashSet<>();
            roles2.add(roleRepository.findByName(ERole.CUSTOMER).orElseThrow());

            userRepository.save(
                    User.builder()
                            .id(2L)
                            .username("user")
                            .password("user")
                            .email("user@user.com")
                            .roles(roles2)
                            .build()
            );
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void bootstrapItems() {

        Item item1 = Item.builder()
                .id(1L)
                .name("Item 1")
                .description("Description 1")
                .build();

        itemRepository.save(item1);

        Item item2 = Item.builder()
                .id(2L)
                .name("Item 2")
                .description("Description 2")
                .build();

        itemRepository.save(item2);


    }
}