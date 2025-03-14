package id.asqi.idesa.bumdes.core.controller;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.Helper;
import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.auth.UserDetailsImpl;
import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.model.AlamatDesa;
import id.asqi.idesa.bumdes.model.Jabatan;
import id.asqi.idesa.bumdes.model.Penduduk;
import id.asqi.idesa.bumdes.model.UserBumdes;
import id.asqi.idesa.bumdes.repository.AlamatDesaRepository;
import id.asqi.idesa.bumdes.repository.JabatanRepository;
import id.asqi.idesa.bumdes.repository.PendudukRepository;
import id.asqi.idesa.bumdes.repository.UserBumdesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {
	private final UserBumdesRepository userBumdesRepository;
	private final PasswordEncoder passwordEncoder;
	private final AlamatDesaRepository alamatDesaRepository;
	private final JabatanRepository jabatanRepository;
	private final PendudukRepository pendudukRepository;

	@GetMapping
	public Object test (
			@RequestBody Map<String, String> req
			) {
		this.testAuthentication();
		return passwordEncoder.matches(req.get("raw"), req.get("hashed"));
	}

	@GetMapping("principal")
	public Object testPrincipal () {
		return Auth.getUserBumdes();
	}

	@GetMapping("alamat-desa")
	public Object alamatDesa () {
		return Auth.getAlamatDesa();
	}

	@GetMapping("protected")
	public Object testProtected (Principal principal) {
		return principal;
	}


	@GetMapping("generate-password")
	public Object generatePassword (@RequestBody String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	@GetMapping("find-by-username")
	public Object findByUsername (@RequestBody String username) {
		return userBumdesRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@GetMapping("generate-user-bumdes")
	public Object generateUserBumdes () {
		long userBumdesCount = userBumdesRepository.count();
		String username = "test" + (userBumdesCount + 1);

		Jabatan jabatan = jabatanRepository.findAll().get(0);
		AlamatDesa alamatDesa = alamatDesaRepository.findById(3203012001L).get();

		UserBumdes ub = new UserBumdes();
		ub.setId(Constants.idGenerator());
		ub.setUsername(username);
		ub.setPassword(passwordEncoder.encode("1234"));
		ub.setAlamat("Jl. Jalan");
		ub.setJabatan(jabatan);
		ub.setAlamatDesa(alamatDesa);
		ub.setTanggalDibuat(LocalDateTime.now());
		userBumdesRepository.save(ub);

		return CommonResponse.data(
				ub,
				"Password -> '1234'"
		);
	}

	@GetMapping("generate-penduduk")
	public Object generatePenduduk () {
		long count = pendudukRepository.count();
		String username = "penduduk" + (count + 1);

		AlamatDesa alamatDesa = alamatDesaRepository.findById(3203012001L).get();

		Penduduk p = new Penduduk();
		p.setId(Constants.idGenerator());
		p.setNik(Helper.generateRandomNik());
		p.setNama(username);
		p.setAlamatDesa(alamatDesa);
		return pendudukRepository.save(p);
	}

	public void testAuthentication () {
		UserBumdes user = userBumdesRepository.findByUsername("test4").get();
		UserDetailsImpl userDetails = UserDetailsImpl.build(user);

		assert userDetails.getUsername().equals(user.getUsername()); // Verify ID
		passwordEncoder.matches("1234", userDetails.getPassword());
	}
}