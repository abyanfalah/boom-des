package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.repository.UserBumdesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBumdesService {
	private final UserBumdesRepository userBumdesRepository;

}