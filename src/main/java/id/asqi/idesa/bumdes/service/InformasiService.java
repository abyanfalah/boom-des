package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.repository.InformasiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InformasiService {
	private final InformasiRepository informasiRepository;

}