package org.mik.first.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationRequst {


    @NotNull
    @Size(min = 1, max = 30)
    private String username;

    @NotNull
    @Size(min = 1, max = 30)
    private String password;

}
