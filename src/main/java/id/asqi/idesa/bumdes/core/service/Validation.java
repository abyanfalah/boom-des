package id.asqi.idesa.bumdes.core.service;

import id.asqi.idesa.bumdes.core.Constants;
import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;

import java.time.LocalDateTime;

public class Validation {
	public static <T> void deletionCheck(boolean isDeleted, Class<T> clazz) {
		if(!isDeleted) return;

		String className = Constants.pascalToSentenceCase(clazz.getSimpleName());

		throw new InvalidOperationException(className.concat(" ini sudah dihapus"));
	}

	public static <T> void deletionCheck(LocalDateTime deletedAt, Class<T> clazz) {
		if(deletedAt == null) return;

		String className = Constants.pascalToSentenceCase(clazz.getSimpleName());

		throw new InvalidOperationException(className.concat(" ini sudah dihapus"));
	}
}