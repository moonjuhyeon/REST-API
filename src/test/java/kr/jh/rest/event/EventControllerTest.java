package kr.jh.rest.event;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void createEvent() throws Exception {
    // given
    EventReqDto eventReqDto = EventReqDto.builder()
        .name("test-name")
        .description("test-desc")
        .beginEnrollmentDateTime(LocalDateTime.of(2021, 9, 13, 16,30))
        .closeEnrollmentDateTime(LocalDateTime.of(2021, 9, 13, 16,50))
        .beginEventDateTime(LocalDateTime.of(2021, 9, 13, 16,30))
        .endEventDateTime(LocalDateTime.of(2021, 9, 13, 16,50))
        .basePrice(100)
        .maxPrice(200)
        .limitOfEnrollment(100)
        .location("jung-ja station")
        .build();

    // then
    mockMvc.perform(post("/api/events/")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaTypes.HAL_JSON)
            .content(objectMapper.writeValueAsString(eventReqDto)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("id").exists())
        .andExpect(jsonPath("id").value(Matchers.not(100)))
        .andExpect(jsonPath("free").value(Matchers.not(true)))
        .andExpect(header().exists(HttpHeaders.LOCATION))
        .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
        .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.toString()));
  }

  @Test
  public void createEvent_Bad_Request() throws Exception {
    // given
    Event event = Event.builder()
        .id(100)
        .name("test-name")
        .description("test-desc")
        .beginEnrollmentDateTime(LocalDateTime.of(2021, 9, 13, 16,30))
        .closeEnrollmentDateTime(LocalDateTime.of(2021, 9, 13, 16,50))
        .beginEventDateTime(LocalDateTime.of(2021, 9, 13, 16,30))
        .endEventDateTime(LocalDateTime.of(2021, 9, 13, 16,50))
        .basePrice(100)
        .maxPrice(200)
        .limitOfEnrollment(100)
        .location("jung-ja station")
        .free(true)
        .eventStatus(EventStatus.PUBLISHED)
        .build();

    // when

    // then
    mockMvc.perform(post("/api/events/")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaTypes.HAL_JSON)
        .content(objectMapper.writeValueAsString(event)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }
}
