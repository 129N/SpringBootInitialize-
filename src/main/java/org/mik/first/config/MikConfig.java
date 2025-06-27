package org.mik.first.config;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "mik")
@Configuration
@Getter
@Setter

public class MikConfig {

    private RemoteConnect remote;

    @Data
    public static class RemoteConnect {
        private final String host;
        private final Integer port;
        private final String baseUrl;
        private final String username;
        private final String password;

    }
}
