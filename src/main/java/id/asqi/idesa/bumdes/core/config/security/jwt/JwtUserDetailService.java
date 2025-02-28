package id.asqi.idesa.bumdes.core.config.security.jwt;


import id.asqi.idesa.bumdes.core.auth.UserDetailsImpl;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.model.UserBumdes;
import id.asqi.idesa.bumdes.repository.UserBumdesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {
    private final UserBumdesRepository userBumdesRepository;

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserBumdes userBumdes = userBumdesRepository.findByUsername(username).orElseThrow(() -> new NotFoundEntity("User Not Found with username: " + username));
//        return UserDetailsImpl.build(userBumdes);
//    }

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBumdes u = userBumdesRepository.findByUsername(username).orElseThrow(() -> new NotFoundEntity("User Not Found with username: " + username));
        return UserDetailsImpl.build(u);
    }
}