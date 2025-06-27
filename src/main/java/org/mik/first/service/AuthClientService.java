package org.mik.first.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mik.first.component.JwtInterceptor;
import org.mik.first.config.MikConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
@Getter

public class AuthClientService {

    private final MikConfig mikConfig;
    private final RestTemplate restTemplate;

    private boolean connected;
    private LocalDateTime lastRefreshed;

    public void connect() {
        refreshToken();
    }


    private void refreshToken(){
        this.connected=false;
        getJwtToken().ifPresent(t->{
            //      this.jwtInterceptor.setToken(t);
            this.connected=true;
        });
    }


    private Optional<String> getJwtToken(){

        String authUrl = "https://%s:%d/%s/login".formatted(
                mikConfig.getRemote().getHost(),
                mikConfig.getRemote().getPort(),
                mikConfig.getRemote().getBaseUrl()
        );



        Map<String, String> requestBody = Map.of(
            "username", mikConfig.getRemote().getUsername(),
            "password", mikConfig.getRemote().getPassword()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String,String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response=restTemplate.postForEntity(authUrl,
                request, Map.class);
        if (response.getStatusCode().isError() || response.getBody()==null)
            return Optional.empty();

        Object token = response.getBody().get("token");
        return token != null
                ? Optional.of(token.toString())
                : Optional.empty();

    }



}
