package id.asqi.idesa.bumdes.core.auth;

import id.asqi.idesa.bumdes.model.AlamatDesa;
import id.asqi.idesa.bumdes.model.UserBumdes;
import org.springframework.security.core.context.SecurityContextHolder;

public class Auth {
	public static UserBumdes getUserBumdes() {
		return  ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserBumdes();
	}

	public static Object getPrincipal() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public static Long id () {
		return getUserBumdes().getId();
	}

	public static String username () {
		return getUserBumdes().getUsername();
	}
	public static AlamatDesa getAlamatDesa () {
		return getUserBumdes().getAlamatDesa();
	}
	public static Long getAlamatDesaId () {
		return getUserBumdes().getAlamatDesa().getId();
	}


}