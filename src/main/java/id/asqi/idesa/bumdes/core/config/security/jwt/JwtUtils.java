package id.asqi.idesa.bumdes.core.config.security.jwt;

import id.asqi.idesa.bumdes.core.auth.UserDetailsImpl;
import id.asqi.idesa.bumdes.core.service.EnvService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
  private final EnvService envService;

  public String generateJwt(UserDetailsImpl user) {
    return Jwts.builder()
        .setSubject((user.getUsername()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + envService.jwtExpirationMs))
        .signWith(key(), SignatureAlgorithm.HS256)
        .compact();
  }

  public Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(envService.jwtSecret));
  }

  public String extractUsername(String jwt) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
               .parseClaimsJws(jwt).getBody().getSubject();
  }
}