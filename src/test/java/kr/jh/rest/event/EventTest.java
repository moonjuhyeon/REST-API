package kr.jh.rest.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Entity 테스트")
class EventTest {

  @Test
  @DisplayName("Entity Builder 테스트")
  void builder(){
    Event event = Event.builder()
        .name("tdd-Class")
        .description("REST API TDD CLASS")
        .build();
    assertThat(event).isNotNull();
  }

  @Test
  @DisplayName("Entity Java Bean 테스트")
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