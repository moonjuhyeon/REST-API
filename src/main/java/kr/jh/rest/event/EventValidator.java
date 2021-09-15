package kr.jh.rest.event;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class EventValidator {

  public void validate(EventReqDto eventReqDto, Errors errors){
    if(eventReqDto.getBasePrice() > eventReqDto.getMaxPrice() && eventReqDto.getMaxPrice() != 0){
      errors.reject("wrongPrices", "Prices are Wrong.");
    }

    LocalDateTime endEventTime = eventReqDto.getEndEventDateTime();
    if(endEventTime.isBefore(eventReqDto.getBeginEventDateTime())||
    endEventTime.isBefore(eventReqDto.getCloseEnrollmentDateTime())||
    endEventTime.isBefore(eventReqDto.getBeginEventDateTime())){
      errors.rejectValue("endEventDateTime", "wrongValue", "EndEventDateTime is Wrong.");
    }

    LocalDateTime beginEventDateTime = eventReqDto.getBeginEventDateTime();
    if(beginEventDateTime.isAfter(eventReqDto.getEndEventDateTime())||
    beginEventDateTime.isAfter(eventReqDto.getCloseEnrollmentDateTime())){
      errors.rejectValue("beginEventDateTime", "wrongValue", "BeginEventDateTime is Wrong");
    }
  }
}
