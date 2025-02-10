package id.asqi.idesa.bumdes.core.component.exception;

public class InvalidOperationException extends RuntimeException {
	public InvalidOperationException (String message) {
		super("Invalid Operation: " + message);
	}
}