package org.example.springskeleton.example;

import org.example.springskeleton.core.SpringControllerBaseTest;
import org.example.springskeleton.example.dto.ExampleDto;
import org.example.springskeleton.example.dto.ExampleRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;

import static org.example.springskeleton.globals.UrlMapping.EXAMPLE_ID_PATH;
import static org.example.springskeleton.globals.UrlMapping.EXAMPLE_PATH;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExampleControllerTest extends SpringControllerBaseTest {
  @InjectMocks
  private ExampleController exampleController;

  @Mock
  private ExampleService exampleService;

  @BeforeEach
  public void setUp() {
    super.setUp();
    exampleController = new ExampleController(exampleService);
    mvc = buildForController(exampleController);
  }

  @Test
  void create() throws Exception {
    ExampleRequestDto createRequestDto = ExampleRequestDto.builder().name("name").build();

    ExampleDto result = ExampleDto.builder().id(1L).name("name").build();

    when(exampleService.create(createRequestDto.getName()))
        .thenReturn(result);

    ResultActions res = performPostWithRequestBody(EXAMPLE_PATH, createRequestDto);

    verify(exampleService, times(1)).create(createRequestDto.getName());

    res.andExpect(status().isOk()).andExpect(contentToBe(result));
  }

  @Test
  void findById() throws Exception {
    Long id = 1L;
    ExampleDto result = ExampleDto.builder().id(id).name("name").build();
    ResponseEntity<ExampleDto> responseResult = ResponseEntity.ok(result);

    when(exampleService.findById(id))
        .thenReturn(responseResult);

    ResultActions res = performGet(EXAMPLE_PATH + EXAMPLE_ID_PATH, id);

    verify(exampleService, times(1)).findById(id);

    res.andExpect(status().isOk()).andExpect(contentToBe(result));
  }

  @Test
  void findByIdNotFound() throws Exception {
    Long id = 1L;
    ResponseEntity<ExampleDto> responseResult = ResponseEntity.notFound().build();

    when(exampleService.findById(id))
        .thenReturn(responseResult);

    ResultActions res = performGet(EXAMPLE_PATH + EXAMPLE_ID_PATH, id);

    verify(exampleService, times(1)).findById(id);

    res.andExpect(status().isNotFound());
  }
}