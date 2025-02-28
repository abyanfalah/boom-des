package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.repository.RekeningUserBumdesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RekeningUserBumdesService {
	private final RekeningUserBumdesRepository rekeningUserBumdesRepository;

}