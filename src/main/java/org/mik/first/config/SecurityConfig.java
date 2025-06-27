package org.mik.first.config;


import lombok.RequiredArgsConstructor;
import org.mik.first.Const;
import org.mik.first.component.JwtAuthnticationFilter;
import org.mik.first.service.JWTservice;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//ddddd ctrl + / is the comment out
public class SecurityConfig {


    private final JwtAuthnticationFilter authnticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JWTservice jwTservice;
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(

                        r-> r
                                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                                .requestMatchers(  new AntPathRequestMatcher(Const.REST_API+"/api/v1/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/actuator/**")).permitAll()

                                .requestMatchers(new AntPathRequestMatcher(Const.REST_API+"/country/**"))
                                .hasAnyRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher(Const.REST_API+"/client/**"))
                                .hasAnyAuthority("ADMIN", "USER")

                                .requestMatchers(new AntPathRequestMatcher(Const.REST_API+"/person/**"))
                                .hasAnyAuthority("ADMIN", "USER")

                                .requestMatchers(new AntPathRequestMatcher(Const.REST_API+"/company/**"))
                                .hasAnyAuthority("ADMIN", "USER")
                .anyRequest() //.authenticated()
                .permitAll()
                )
                .sessionManagement(sess->
                        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authnticationFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(h->h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
                //.httpBasic(Customizer.withDefaults());
        return http.build();
    }

}

/*
*
*
* .authorizeHttpRequests(

                        r-> r.requestMatchers(
                                new AntPathRequestMatcher("/h2-console/**"),
                                new AntPathRequestMatcher(Const.REST_API+"/api/v1/**"),
                                new AntPathRequestMatcher("/actuator/**")
                                ).permitAll()

                                .requestMatchers(new AntPathRequestMatcher(Const.REST_API+"/country/**"))
                                .hasAnyRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher(Const.REST_API+"/client/**"))
                                .hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated()
                )
* */