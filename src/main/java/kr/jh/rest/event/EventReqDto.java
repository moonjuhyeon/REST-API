package kr.jh.rest.event;

import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventReqDto {
  @NotEmpty
  private String name;
  @NotEmpty
  private String description;
  @NotNull
  private LocalDateTime beginEnrollmentDateTime;
  @NotNull
  private LocalDateTime closeEnrollmentDateTime;
  @NotNull
  private LocalDateTime beginEventDateTime;
  @NotNull
  private LocalDateTime endEventDateTime;

  private String location;
  @Min(0)
  private int basePrice;
  @Min(0)
  private int maxPrice;
  @Min(0)
  private int limitOfEnrollment;
}
