package org.mik.first.dto;


import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PersonDTO extends AbstractDTO<Long>{

    @Valid
    private ClientDTO client;


    @NotNull(message = "PersonalId is required")
    @Pattern(regexp = "\\d{12}", message = "Must be exactly 12 digits")
    private String personalID;

    @NotNull(message = "Birthdate is required")
    @Past(message = "Birthdate must be in the past" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
