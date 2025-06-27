package org.mik.first.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode (callSuper = true)
public class ClientDTO extends AbstractDTO<Long>{
    //private String  id;

    @NotNull(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters long")
    private String name;

    @NotNull(message = "Address is required")
    @Size(min = 1, max = 100, message = "Address must be between 1 and 100 characters long")
    private String address;

    @NotNull(message = "Country is required")
    private Long countryID;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be greater than or equal to 1")
    @Max(value = 1000000, message = "Amount must be less than or equal to 1000000")
    private Integer amount;

    private String countryName;
}
