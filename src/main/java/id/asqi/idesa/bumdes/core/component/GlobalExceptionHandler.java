package id.asqi.idesa.bumdes.core.component;


import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;
import id.asqi.idesa.bumdes.core.component.exception.NotFoundEntity;
import id.asqi.idesa.bumdes.core.component.logging.ErrorLogger;
import id.asqi.idesa.bumdes.core.http.CommonResponse;
import id.asqi.idesa.bumdes.core.service.EnvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.*;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	private final EnvService envService;
	private final List<String> message = new ArrayList<>();

	@ExceptionHandler(ServletRequestBindingException.class)
	public ResponseEntity<?> handleServletRequestBindingException (ServletRequestBindingException ex) {
		return CommonResponse.simpleResponse(ex.getMessage());
	}

	@ExceptionHandler(NotFoundEntity.class)
	public ResponseEntity<?> notFoundEntityExceptionHandler (NotFoundEntity e) {
		ErrorLogger.printFilteredStackTrace(e, this.getClass().getPackageName());
		return CommonResponse.noData(e.getMessage());
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> badCredentialsExceptionHadler (BadCredentialsException e) {
		ErrorLogger.printFilteredStackTrace(e, this.getClass().getPackageName());
		return CommonResponse.badRequest(e.getMessage());
	}

	@ExceptionHandler(InvalidOperationException.class)
	public ResponseEntity<?> invalidOperationExceptionHandler (InvalidOperationException e) {
		ErrorLogger.printFilteredStackTrace(e, this.getClass().getPackageName());
		return CommonResponse.badRequest(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler (Exception e) {
		ErrorLogger.printFilteredStackTrace(e, this.getClass().getPackageName());
//		e.printStackTrace();
		return CommonResponse.serverError(e.getMessage());
	}

	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> NoResourceFoundExceptionHandler (NoResourceFoundException e) {
		return CommonResponse.noEndpoint("endpoint tidak ditemukan: " + e.getResourcePath());
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<?> MaxUploadSizeExceededExceptionException (MaxUploadSizeExceededException ex) {
		return CommonResponse.simpleResponse("Jumlah Upload File Terlalu Besar Harus Kurang Dari 2MB" + ex.getMessage());
	}

	/*Request validation exception response*/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException (MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult(); List<FieldError> fieldErrors = result.getFieldErrors();

		Map<String, String> validationMessages = new HashMap<>(); for (FieldError fieldError : fieldErrors) {
			String errorMessage; if (Objects.equals(fieldError.getCode(), "typeMismatch")) {
				errorMessage = "Type data salah";
			} else {
				errorMessage = fieldError.getDefaultMessage();
			} validationMessages.put(fieldError.getField(), errorMessage);
		} return CommonResponse.validationError(validationMessages);
	}
}