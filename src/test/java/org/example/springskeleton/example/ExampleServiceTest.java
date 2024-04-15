package org.example.springskeleton.example;

import org.example.springskeleton.core.SpringUnitBaseTest;
import org.example.springskeleton.example.dto.ExampleDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ExampleServiceTest extends SpringUnitBaseTest {

  @InjectMocks
  // makes it so you don't have to manually create the object, all the dependencies are injected from the mocks defined below
  private ExampleService exampleService;

  @Mock
  private ExampleRepository exampleRepository;

  @Test
  void create() {
    String name = "name";
    Example example = Example.builder().name(name).build();
    Example savedInstance = Example.builder().id(1L).name(name).build();

    when(exampleRepository.save(example)).thenReturn(savedInstance);

    ExampleDto result = exampleService.create(name);

    assertEquals(savedInstance.getId(), result.getId());
    assertEquals(savedInstance.getName(), result.getName());
  }

  @Test
  void findById() {
    Long id = 1L;
    String name = "name";
    Example example = Example.builder().id(id).name(name).build();

    when(exampleRepository.findById(id)).thenReturn(of(example));

    ResponseEntity<ExampleDto> result = exampleService.findById(id);

    ExampleDto body = result.getBody();
    assertEquals(example.getId(), body.getId());
    assertEquals(example.getName(), body.getName());
  }
}