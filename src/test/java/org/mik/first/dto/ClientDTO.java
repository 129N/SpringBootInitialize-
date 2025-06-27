package org.mik.first.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode (callSuper = true)
public class ClientDTO extends AbstractDTO<Long>{
    private String name;
    private String address;
    private Long countryID;
    private Integer amount;
}
