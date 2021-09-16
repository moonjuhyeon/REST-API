package kr.jh.rest.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runners.Parameterized.Parameters;

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

  @ParameterizedTest
  @CsvSource({
      "0, 0, true",
      "100, 0, false",
      "0, 100, false",
      "100, 100, false"
  })
  @DisplayName("Entity free field 변경 테스트")
  void testFree(int basePrice, int maxPrice, boolean free){
    // given
    Event event = Event.builder()
        .basePrice(basePrice)
        .maxPrice(maxPrice)
        .build();

    // when
    event.update();

    // then
    assertThat(event.isFree()).isEqualTo(free);
  }

  @ParameterizedTest
  @CsvSource(value={
      "korea, true",
      " , false",
      "null,false",
  }, nullValues={"null"})
  @DisplayName("Entity free offline 변경 테스트")
  void testOffline(String location, boolean offline){
    // given
    Event event = Event.builder()
        .location(location)
        .build();
    // when
    event.update();
    // then
    assertThat(event.isOffline()).isEqualTo(offline);
  }
}