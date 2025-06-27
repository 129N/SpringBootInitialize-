package org.mik.first.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mik.first.annotation.LogInfo;
import io.jsonwebtoken.security.Keys;
import org.mik.first.domain.UserDomain;
import org.mik.first.repository.UserDomainRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class JWTservice {

     private final PasswordEncoder passwordEncoder;
     private final UserDomainRepository userDomainRepository;

     @Value("${spring.application.security.jwt.secret-key}")
     private String secretKey;

     @Value("${spring.application.security.jwt.secret-key}")
     private long jwtExpiration;


     @Value("${spring.application.security.jwt.expiration}")
     private long expiration;

     @PostConstruct
     public void init() {
          String pwd = passwordEncoder.encode("admin");
     pwd =passwordEncoder.encode("user");
     }

     public UserDomain createUser(UserDomain userDomain) {
          String pwd = passwordEncoder.encode(userDomain.getPassword());
          userDomain.setPassword(pwd);
          return userDomainRepository.save(userDomain);
     }
     @Transactional
     public UserDomain updatePassword(Long userId, String newpassword){
          UserDomain user = userDomainRepository.findById(userId)
                  .orElseThrow(()-> new UsernameNotFoundException("Cannot find user by id"+ userId));
          user.setPassword(passwordEncoder.encode(newpassword));
          return userDomainRepository.save(user);
     }

     public String extraUsername(String token){

          return extraClaim(token, Claims::getSubject);
     }

     public <T> T extraClaim(String token, Function<Claims, T> claimsResolver) {
          final Claims claims = extractAllcclaims(StringUtils.trimAllWhitespace(token));

          return claimsResolver.apply(claims);
     }




     @LogInfo
     public String generateToken(String username) {

          return generateToken(new HashMap<>(), username);
     }

     private String generateToken(Map<String, Object> extraClaims, String username){
          String token=Jwts.builder()
                  .setClaims(extraClaims)
                  .setSubject(username)
                  .setIssuedAt( new Date(System.currentTimeMillis()))
                  .setExpiration(new Date(System.currentTimeMillis() + expiration*1000 ) )
                  .signWith(SignatureAlgorithm.HS512, secretKey)
                  .compact();
          log.info(token);
          return token;
     }


     public boolean isTokenValid(String token, UserDetails userDetails) {
          String username=extraUsername(token);
          return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
     }

     public boolean isTokenExpired(String token) {
          try{
               return extarctExpiration(token).before(new Date());
          } catch (ExpiredJwtException e){
               log.info("Token is expired");
               return true;
          }
     }


     private Date extarctExpiration(String token) {
          return extraClaim(token, Claims::getExpiration);
     }


     private Claims extractAllcclaims(String token) {
          return Jwts
                  .parser()
                  .setSigningKey(secretKey)
                  .build()
                  .parseClaimsJwt(token)
                  .getBody();
     }


     private Key getSignInKey() {

          return Keys.hmacShaKeyFor(this.secretKey.getBytes());
     }

}

//spring.application.security.jwt.secret-key_