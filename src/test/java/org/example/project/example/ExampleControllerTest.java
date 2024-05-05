package org.example.project.example;

import org.example.project.core.SpringControllerBaseTest;

class ExampleControllerTest extends SpringControllerBaseTest {
 /* @InjectMocks
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
  }*/
}