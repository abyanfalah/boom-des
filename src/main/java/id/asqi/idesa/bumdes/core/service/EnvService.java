package id.asqi.idesa.bumdes.core.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class EnvService {

	private String prodProfileName = "prod";
	private String devProfileName = "dev";

	public boolean isProd () {
		return Objects.equals(this.profile.trim(), prodProfileName);
	}
	public boolean isDev () {return Objects.equals(this.profile.trim(), devProfileName);}


	@Value("${jwt.secret}")
	public String jwtSecret;

	@Value("${jwt.expirationInMs}")
	public int jwtExpirationMs;

	@Value("${jwt.refreshCookieName}")
	public String jwtRefreshCookie;

	@Value("${spring.profiles.active}")
	public String profile;


	@Value("${cookie.key}")
	public String cookieKey;

	@Value("${cookie.expirationInMs}")
	public int cookieExpirationInMs;

	@Value("${minio.access-key}")
	public String minioAccessKey;

	@Value("${minio.secret-key}")
	public String minioSecretKey;

	@Value("${minio.url}")
	public String minioUrl;


	@Value("${minio.bucket-name}")
	public String bucketName;
}