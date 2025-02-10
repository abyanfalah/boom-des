package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.UserBumdes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBumdesRepository extends JpaRepository<UserBumdes, Long> {
		Optional<UserBumdes> findByUsername (String username);

}