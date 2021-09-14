package kr.jh.rest.event;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Event {

  @Id
  private Integer id;
  private boolean offline;
  private boolean free;
  @Enumerated(EnumType.STRING)
  private EventStatus eventStatus;
  private String name;
  private String description;
  private LocalDateTime beginEnrollmentDateTime;
  private LocalDateTime closeEnrollmentDateTime;
  private LocalDateTime beginEventDateTime;
  private LocalDateTime endEventDateTime;
  private int basePrice;
  private int maxPrice;
  private String location;
  private int limitOfEnrollment;

}
