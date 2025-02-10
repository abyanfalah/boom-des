package id.asqi.idesa.bumdes.core.config.security;

import id.asqi.idesa.bumdes.core.component.logging.HttpLogger;
import id.asqi.idesa.bumdes.core.config.security.jwt.JwtAuthTokenFilter;
import id.asqi.idesa.bumdes.core.config.security.jwt.JwtAuthenticationEntryPoint;
import id.asqi.idesa.bumdes.core.config.security.jwt.JwtUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	private final JwtUserDetailService jwtUserDetailService;
	private final HttpLogger httpLogger;

	private final JwtAuthTokenFilter jwtAuthTokenFilter;

	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint () {
		return new JwtAuthenticationEntryPoint();
	}

	@Bean
	public AuthenticationProvider authenticationProvider () {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(jwtUserDetailService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable);
		http.cors(AbstractHttpConfigurer::disable);

		http.authorizeHttpRequests(
				auth -> auth
						.requestMatchers("/api/v1/auth/register").permitAll()

						.requestMatchers("/api/v1/auth/login").permitAll()
						.requestMatchers("/api/test/**").permitAll()
						.anyRequest().authenticated()
		);

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.exceptionHandling((exception) -> exception.authenticationEntryPoint(authenticationEntryPoint()));
		http.authenticationProvider(authenticationProvider());

		/*uses httpLogger BEFORE UsernamePasswordAuthenticationFilter*/
		http.addFilterBefore(httpLogger, UsernamePasswordAuthenticationFilter.class);

		/*uses jwtAuthTokenFilter AFTER UsernamePasswordAuthenticationFilter*/
		http.addFilterAfter(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);


		return http.build();
	}

}