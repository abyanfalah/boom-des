package id.asqi.idesa.bumdes.core.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.asqi.idesa.bumdes.model.UserBumdes;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	private String jabatan;

	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities () {
		return null;
	}

	@Override
	public String getPassword () {
		return null;
	}

	@Override
	public String getUsername () {
		return null;
	}

	public UserDetailsImpl (
			Long id,
			String username,
			String password,
//			String jabatan,
			Collection<? extends GrantedAuthority> authorities
	) {
		this.id = id;
		this.username = username;
		this.password = password;
//		this.jabatan = jabatan;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build (UserBumdes user) {
//		List<GrantedAuthority> userRoles = user.getJabatan().stream()
//				.map(jabatan -> new SimpleGrantedAuthority("ROLE_" + jabatan.getName().toUpperCase()))
//				.collect(Collectors.toList());
//		combinedAuthorities.addAll(userRoles);

//
//		List<GrantedAuthority> userPermissions = user.getPermissions().stream()
//				.map(permission -> new SimpleGrantedAuthority(permission.getName()))
//				.collect(Collectors.toList());
//
//		List<GrantedAuthority> rolePermissions = user.getJabatan().stream()
//				.map(Jabatan::getPermissions)
//				.flatMap(Collection::stream)
//				.map(permission -> new SimpleGrantedAuthority(permission.getName()))
//				.collect(Collectors.toList());


		List<GrantedAuthority> combinedAuthorities = new ArrayList<>();
//		combinedAuthorities.addAll(userPermissions);
//		combinedAuthorities.addAll(rolePermissions);

//		List<String> jabatan = user.getJabatan().stream()
//				.map(Jabatan::getName)
//				.collect(Collectors.toList());

		List<GrantedAuthority> jabatan = new ArrayList<>();


		return new UserDetailsImpl(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				combinedAuthorities
		);
	}
}