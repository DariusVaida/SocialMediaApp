package org.example.springskeleton.example;

import org.example.springskeleton.core.SpringIntegrationBaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExampleRepositoryIntegrationTest extends SpringIntegrationBaseTest {

  @Autowired
  private ExampleRepository exampleRepository;

  @Test
  void findByName() {
    String name = "name";
    Example example = Example.builder().name(name).build();
    exampleRepository.save(example);

    assertTrue(exampleRepository.findByName(name).isPresent());
    assertFalse(exampleRepository.findByName("not found").isPresent());
  }
}