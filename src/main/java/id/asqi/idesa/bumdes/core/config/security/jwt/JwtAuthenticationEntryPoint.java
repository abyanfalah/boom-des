package id.asqi.idesa.bumdes.core.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.http.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Response<?> data = CommonResponse.unauthenticated().getBody();
        ObjectMapper mapper = new ObjectMapper();
        OutputStream out = response.getOutputStream();
        mapper.writeValue(out, data);
    }
}