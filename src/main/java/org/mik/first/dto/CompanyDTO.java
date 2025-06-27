package org.mik.first.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;
import lombok.experimental.SuperBuilder;
import jakarta.validation.Valid;
import lombok.*;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyDTO extends AbstractDTO<Long>{


    @Valid
    private ClientDTO client;

    @NotNull(message = "Established is required")
    private Integer established;


    @NotNull (message = "Ca@italization is required")
    private Long capitalization;

    @NotNull(message = "TaxId is required")
    @Size(min = 8, max = 9, message = "TaxId must be exactly 9 characters long")
    private String taxId;

}
