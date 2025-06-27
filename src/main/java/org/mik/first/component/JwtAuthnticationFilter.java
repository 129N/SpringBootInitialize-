package org.mik.first.component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.mik.first.service.JWTservice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthnticationFilter extends OncePerRequestFilter {

    private final JWTservice jwTservice;
    private final UserDetailsService userDetailsService;

    @Value("${spring.application.security.jwt.secret-key_}")
    private String headerString;

    @Value("${spring.application.security.jwt.secret-key_}")
    private String tokenPrefix;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String userUsername;
        final String username ;
        String token = extractTokenFromRequest(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }


        username = jwTservice.extraUsername(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwTservice.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authntication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authntication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authntication);
            }

        }
        filterChain.doFilter(request, response);
    }

        private String extractTokenFromRequest (HttpServletRequest request){
            String authHeader = request.getHeader(headerString);
            if (authHeader != null && authHeader.startsWith(tokenPrefix)) {
                return authHeader.substring(tokenPrefix.length() + 1);
            }


            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("jwt")) {
                        String jwt = cookie.getValue();
                            if(!StringUtils.hasLength(jwt))
                                return null;
                        return jwTservice.isTokenExpired(jwt) ? null : jwt;
                    }
                }
            }

            return null;
        }
    }

