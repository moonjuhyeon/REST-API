package kr.jh.rest.event;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api/events", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class EventController {

  private final EventRepository eventRepository;

  private final ModelMapper modelMapper;

  private final EventValidator eventValidator;

  @PostMapping
  public ResponseEntity createEvent(
      @RequestBody @Valid EventReqDto eventReqDto, Errors errors
  ){
    if(errors.hasErrors()){
      return ResponseEntity.badRequest().body(errors);
    }

    eventValidator.validate(eventReqDto, errors);
    if(errors.hasErrors()){
      return ResponseEntity.badRequest().body(errors);
    }

    Event event = modelMapper.map(eventReqDto, Event.class);
    event.update();
    Event newEvent = eventRepository.save(event);
    URI createdUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
    return ResponseEntity.created(createdUri).body(newEvent);
  }
}
