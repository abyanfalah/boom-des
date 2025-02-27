package id.asqi.idesa.bumdes.repository;

import id.asqi.idesa.bumdes.model.UserBumdes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserBumdesRepository extends JpaRepository<UserBumdes, Long> {

		@Query("SELECT u FROM UserBumdes u WHERE u.username = :username")
		Optional<UserBumdes> findByUsername (String username);

}