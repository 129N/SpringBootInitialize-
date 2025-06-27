package org.mik.first.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class AuthenticationResponse {
    private String token;
}
