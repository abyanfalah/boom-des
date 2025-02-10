package id.asqi.idesa.bumdes.core.component.logging;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class ThirdPartyLogger {
	public <ReqT, ResT> void log (String url, HttpEntity<ReqT> req, ResponseEntity<ResT> res) {
		String reqHeaders = req.getHeaders().toString();
		String reqBody = new Gson().toJson(req.getBody());
		String resHeaders = res.getHeaders().toString();
		String resBody = Objects.requireNonNull(res.getBody()).toString();

		log.info("""
						       
						Request to third-party API =====================================================================================================
							URL         : {}
							ReqHeaders  : {}
							ReqBody     : {}
							ResCode     : {}
							ResHeaders  : {}
							ResBody     : {}
						--------------------------------------------------------------------------------------------------------------------------------
							""",
				url,
				reqHeaders,
				reqBody,
				res.getStatusCode(),
				resHeaders,
				resBody


		);


	}
}