package id.asqi.idesa.bumdes.core.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.asqi.idesa.bumdes.model.Jabatan;
import id.asqi.idesa.bumdes.model.UserBumdes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	private Jabatan jabatan;

	private UserBumdes userBumdes;

	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities () {
		return authorities;
	}

	@Override
	public String getPassword () {
		return password;
	}

	@Override
	public String getUsername () {
		return username;
	}

	public UserDetailsImpl (
			Long id,
			String username,
			String password,
			Jabatan jabatan,
			Collection<? extends GrantedAuthority> authorities,
			UserBumdes userBumdes
	) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.jabatan = jabatan;
		this.authorities = authorities;
		this.userBumdes = userBumdes;
	}

	public static UserDetailsImpl build (UserBumdes user) {
		List<GrantedAuthority> combinedAuthorities = new ArrayList<>();

		return new UserDetailsImpl(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				user.getJabatan(),
				combinedAuthorities,
				user
		);
	}

	public UserBumdes getUserBumdes  () {
		UserBumdes u = new UserBumdes();
		u.setId(id);
		u.setUsername(username);
		u.setJabatan(jabatan);
		return u;
	}
}