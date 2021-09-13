package kr.jh.rest.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EventTest {
  @Test
  void builder(){
    Event event = Event.builder()
        .name("tdd-Class")
        .description("REST API TDD CLASS")
        .build();
    assertThat(event).isNotNull();
  }

  @Test
  void javaBean(){
    Event event = new Event();
    String name = "name";
    String description = "des";
    event.setName(name);
    event.setDescription(description);

    assertThat(event.getName()).isEqualTo(name);
    assertThat(event.getDescription()).isEqualTo(description);
  }
}