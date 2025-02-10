package id.asqi.idesa.bumdes.core.config.security.jwt;

import id.asqi.idesa.bumdes.core.service.EnvService;
import id.asqi.idesa.bumdes.model.UserBumdes;
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

  public String generateJwt(UserBumdes user) {
    return Jwts.builder()
        .setSubject((user.getUsername()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + envService.jwtExpirationMs))
        .signWith(key(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(envService.jwtSecret));
  }

  public String extractUsername(String jwt) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
               .parseClaimsJws(jwt).getBody().getSubject();
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(envService.jwtSecret);
    return Keys.hmacShaKeyFor(keyBytes);
  }


  /*will figure out where to use this later*/
    public boolean isValidToken(String authToken) {
      try {
        Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
        return true;
      } catch (MalformedJwtException e) {
        logger.error("Invalid JWT token: {}", e.getMessage());
      } catch (ExpiredJwtException e) {
        logger.error("JWT token is expired: {}", e.getMessage());
      } catch (UnsupportedJwtException e) {
        logger.error("JWT token is unsupported: {}", e.getMessage());
      } catch (IllegalArgumentException e) {
        logger.error("JWT claims string is empty: {}", e.getMessage());
      }
      return false;
    }
}