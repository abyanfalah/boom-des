package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.IdNumberRequest;
import id.asqi.idesa.bumdes.http.request.MetodePengirimanRequest;
import id.asqi.idesa.bumdes.model.MetodePengiriman;
import id.asqi.idesa.bumdes.repository.MetodePengirimanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetodePengirimanService {
	private final MetodePengirimanRepository metodePengirimanRepository;

	public List<MetodePengiriman> getAll () {
		return metodePengirimanRepository.findAll();
	}

	@Transactional
	public void create (MetodePengirimanRequest.Create req) throws Exception {
		MetodePengiriman e = new MetodePengiriman();
		e.setId(Constants.idGenerator());
		e.setNama(req.getNama());

		metodePengirimanRepository.save(e);
	}

	@Transactional
	public void update (MetodePengirimanRequest.Update req) {
		MetodePengiriman e = metodePengirimanRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(MetodePengiriman.class));
		/*valdasi role*/
		e.setNama(req.getNama());
		metodePengirimanRepository.save(e);
	}

	public void delete (IdNumberRequest req) {
		MetodePengiriman e = metodePengirimanRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(MetodePengiriman.class));
		metodePengirimanRepository.delete(e);
	}

}