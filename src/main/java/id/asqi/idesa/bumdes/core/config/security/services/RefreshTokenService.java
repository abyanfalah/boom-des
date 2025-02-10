package id.asqi.idesa.bumdes.core.config.security.services;//package id.asqi.vismon.core.config.security.services;
//
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.util.Calendar;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class RefreshTokenService {
//    @Value("${jwt.expirationRefreshInMs}")
//    private Long refreshTokenInMs;
//
//    private final RefreshTokenRepository refreshTokenRepository;
//
//    private final UserRepository userRepository;
//
//    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
//        this.refreshTokenRepository = refreshTokenRepository;
//        this.userRepository = userRepository;
//    }
//
//    public Optional<RefreshToken> findByToken(String token){
//        return refreshTokenRepository.findByToken(token);
//    }
//
//    public RefreshToken createRefreshToken(String userId){
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_YEAR, 1);
//        Timestamp tomorrow = new Timestamp(calendar.getTimeInMillis());
//
//        RefreshToken refreshToken = new RefreshToken();
//        refreshToken.setId(UUIDGenerator.idGenerator());
//        refreshToken.setUser(userRepository.findById(userId).get());
//        refreshToken.setExpiredDate(String.valueOf(tomorrow));
//        refreshToken.setToken(UUID.randomUUID().toString());
//        refreshToken.setCreatedAt(LocalDate.now());
//        refreshToken.setUpdatedAt(Instant.now());
//
//        refreshToken = refreshTokenRepository.save(refreshToken);
//        return refreshToken;
//    }
//
//    public RefreshToken verifyExpiration(RefreshToken token) {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        if (token.getExpiredDate().compareTo(String.valueOf(timestamp)) < 0) {
//            refreshTokenRepository.delete(token);
//            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
//        }
//        return token;
//    }
//
//    @Transactional
//    public void deleteByUserId(String userId) {
//        refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
//    }
//}