package id.asqi.idesa.bumdes.core.http;


import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.http.request.SetDeleteStatusRequest;
import id.asqi.idesa.bumdes.core.http.response.AuthResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public class CommonResponse {

	private static final Boolean showError = true;

	public static <T> ResponseEntity<Response<T>> validationError (Errors errors) {
		Response<T> response = new Response<>();
		response.setResponseCode(ResponseCode.VALIDATION);
		response.setValidation(Constants.validateErrorMessage(errors));
		return ResponseEntity.ok(response);
	}

	public static <T> ResponseEntity<Response<T>> validationError (Map<String, String> validationErrorMessages) {
		Response<T> response = new Response<>();
		response.setResponseCode(ResponseCode.VALIDATION);
		response.setValidation(validationErrorMessages);
		return ResponseEntity.ok(response);
	}

	public static <T> ResponseEntity<T> directResponse (T data) {
		return ResponseEntity.ok(data);
	}

	public static <T> ResponseEntity<Response<T>> noData () {
		Response<T> r = new Response<>();
		r.setResponseCode(ResponseCode.NO_DATA);
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> noData (String message) {
		Response<T> r = new Response<>();
		r.setResponseCode(ResponseCode.NO_DATA);
		r.setMessage(message);
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> unauthenticated () {
		Response<T> r = new Response<>();
		r.setCode(401);
		r.setMessage("Unauthenticated");
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> unauthenticated (String message) {
		Response<T> r = new Response<>();
		r.setCode(401);
		r.setMessage("Unauthenticated: " + message);
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> forbidden () {
		Response<T> r = new Response<>();
		r.setResponseCode(ResponseCode.FORBIDDEN);
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> forbidden (String message) {
		Response<T> r = new Response<>();
		r.setResponseCode(ResponseCode.FORBIDDEN);
		r.setMessage(message);
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> success () {
		Response<T> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		return ResponseEntity.ok(r);
	}

	public static ResponseEntity<Response<Void>> created (String entityName) {
		Response<Void> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setMessage(entityName.concat(" berhasil disimpan"));
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<Void>> created (Class<T> tClass) {
		Response<Void> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setMessage(Constants.pascalToSentenceCase(tClass.getSimpleName()).concat(" berhasil disimpan"));
		return ResponseEntity.ok(r);
	}

	public static ResponseEntity<Response<Void>> updated (String entityName) {
		Response<Void> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setMessage(entityName.concat(" berhasil diperbarui"));
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<Void>> updated (Class<T> tClass) {
		Response<Void> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setMessage(Constants.pascalToSentenceCase(tClass.getSimpleName()).concat(" berhasil diperbarui"));
		return ResponseEntity.ok(r);
	}

	public static ResponseEntity<Response<Void>> softDeleted (String entityName) {
		Response<Void> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setMessage(entityName.concat(" berhasil dihapus (soft delete)"));
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<Void>> softDeleted (Class<T> tClass) {
		Response<Void> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setMessage(Constants.pascalToSentenceCase(tClass.getSimpleName()).concat(" berhasil dihapus (soft delete)"));
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<Void>> toggledSoftDelete (Class<T> tClass, SetDeleteStatusRequest req) {
		String state = req.getIsDeleted() ? " dihapus (soft delete)" : " di-restore";

		Response<Void> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setMessage(Constants.pascalToSentenceCase(tClass.getSimpleName()).concat(" berhasil".concat(state).concat(".")));
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<Void>> deleted (Class<T> tClass) {
		Response<Void> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setMessage(Constants.pascalToSentenceCase(tClass.getSimpleName()).concat(" berhasil dihapus"));
		return ResponseEntity.ok(r);
	}

	public static ResponseEntity<Response<Void>> success (String message) {
		Response<Void> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setMessage(message);
		return ResponseEntity.ok(r);
	}

	public static ResponseEntity<Response<Void>> restored (String entityName) {
		Response<Void> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setMessage(entityName.concat(" berhasil direstore"));
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<Void>> restored (Class<T> tClass) {
		Response<Void> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setMessage(Constants.pascalToSentenceCase(tClass.getSimpleName()).concat(" berhasil direstore"));
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> serverError (Exception e) {
		Response<T> r = new Response<>();
		r.setResponseCode(ResponseCode.SERVER_ERROR);
		if (showError) {
			r.setMessage(e.getMessage());
			r.setLocation(getErrorLocation(e));
		}
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> serverError (String message) {
		Response<T> r = new Response<>();
		r.setResponseCode(ResponseCode.SERVER_ERROR);
		if (showError) {
			r.setMessage(message);
		}
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> serverError () {
		Response<T> r = new Response<>();
		r.setResponseCode(ResponseCode.SERVER_ERROR);
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> databaseError (Exception e) {
		Response<T> r = new Response<>();
		r.setResponseCode(ResponseCode.DATABASE_ERROR);
		r.setMessage(e.getMessage());
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> data (T data) {
		Response<T> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setData(data);
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> data (T data, String message) {
		Response<T> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setData(data);
		r.setMessage(message);
		return ResponseEntity.ok(r);
	}

	public static <T, D> ResponseEntity<Response<Page<T>>> paginated (Page<T> page, List<D> dtoList) {
		Response<Page<T>> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setData(page, dtoList);
		return ResponseEntity.ok(r);
	}

	public static <P> ResponseEntity<Response<Page<P>>> paginated (Page<P> page) {
		Response<Page<P>> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setPageData(page);
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> badRequest (String message) {
		Response<T> r = new Response<>();
		r.setCode(400);
		r.setMessage(message);
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> badRequest (Exception e) {
		Response<T> r = new Response<>();
		r.setCode(400);
		if (showError) {
			r.setMessage(e.getMessage());
			r.setLocation(getErrorLocation(e));
		}
		return ResponseEntity.ok(r);
	}



	public static <T> ResponseEntity<Response<T>> noEndpoint (String message) {

		Response<T> r = new Response<>();
		r.setCode(404);
		r.setResponseCode(ResponseCode.NO_END_POINT);
		r.setMessage(message);
		return new ResponseEntity<>(r, HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<Response<AuthResponse>> authResponse (AuthResponse authResponse) {
		Response<AuthResponse> r = new Response<>();
		r.setResponseCode(ResponseCode.SUCCESS);
		r.setData(authResponse);
		r.setMessage("authentication success");
		return ResponseEntity.ok(r);
	}

	public static <T> ResponseEntity<Response<T>> logoutResponse (ResponseCookie jwtCookie, ResponseCookie refreshTokenCookie) {
		Response<T> r = new Response<>();
		r.setMessage("logout success");
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString()).body(r);
	}

	public static <T> ResponseEntity<Response<T>> conflict (String message) {
		Response<T> r = new Response<>();
		r.setCode(409);
		r.setMessage(message);
		return ResponseEntity.ok(r);
	}

	public static ResponseEntity<byte[]> spreadSheetResponse (byte[] file, String filename)  {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + filename + "\"");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_XML).body(file);
	}

	public static ResponseEntity<?> simpleResponse (String message) {
		Response<?> r = new Response<>();
		r.setCode(0);
		r.setMessage(message);
		return ResponseEntity.ok(r);
	}


	private static String getErrorLocation (Exception e) {
		StackTraceElement element = e.getStackTrace()[0];
		return element.getClassName() + "." + element.getMethodName() + "():" + element.getLineNumber() + " (for dev purpose only)";
	}

}