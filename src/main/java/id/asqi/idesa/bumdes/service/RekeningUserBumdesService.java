package id.asqi.idesa.bumdes.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.auth.UserDetailsImpl;
import id.asqi.idesa.bumdes.core.component.MyPagination;
import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.http.request.SearchPaginationRequest;
import id.asqi.idesa.bumdes.http.request.RekeningUserBumdesRequest;
import id.asqi.idesa.bumdes.model.RekeningUserBumdes;
import id.asqi.idesa.bumdes.repository.RekeningUserBumdesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RekeningUserBumdesService {
	private final RekeningUserBumdesRepository rekeningUserBumdesRepository;

	public Page<RekeningUserBumdes> getAll(SearchPaginationRequest req){
		return rekeningUserBumdesRepository.search(
				req.getSearch(),
				Auth.id(),
				req.getPagination()
		);
	}

	public void create(RekeningUserBumdesRequest.Create req){
		this.validateRekeningCount();

		RekeningUserBumdes e = new RekeningUserBumdes();
		e.setId(Constants.idGenerator());
		e.setNamaBank(req.getNamaBank());
		e.setAtasNama(req.getAtasNama());
		e.setNomorRekening(req.getNomorRekening());
		e.setUserBumdes(Auth.getUserBumdes());
		e.setTanggalDibuat(LocalDateTime.now());

		rekeningUserBumdesRepository.save(e);
	}

	public void update (RekeningUserBumdesRequest.Update req) {
		RekeningUserBumdes e = rekeningUserBumdesRepository.findById(req.getId()).orElseThrow(() -> new NotFoundEntity(RekeningUserBumdes.class));
		e.setNamaBank(req.getNamaBank());
		e.setAtasNama(req.getAtasNama());
		e.setNomorRekening(req.getNomorRekening());
		rekeningUserBumdesRepository.save(e);
	}

	private void validateRekeningCount(){
		if(!rekeningUserBumdesRepository.hasMaxThreeRekening(Auth.id()))
			throw new InvalidOperationException("User bumdes sudah memilik 3 rekening. Tidak dapat menambah rekening lagi.");
	}


}