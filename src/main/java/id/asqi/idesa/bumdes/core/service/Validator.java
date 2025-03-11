package id.asqi.idesa.bumdes.core.service;

import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;

import java.time.LocalDateTime;

public class Validator {
	public static <T> void deletionCheck(boolean isDeleted, Class<T> clazz) {
		if(!isDeleted) return;

		String className = pascalToSentenceCase(clazz.getSimpleName());

		throw new InvalidOperationException(className.concat(" ini sudah dihapus"));
	}

	public static <T> void deletionCheck(LocalDateTime deletedAt, Class<T> clazz) {
		if(deletedAt == null) return;

		String className = pascalToSentenceCase(clazz.getSimpleName());

		throw new InvalidOperationException(className.concat(" ini sudah dihapus"));
	}

	private static String pascalToSentenceCase (String pascalCaseString) {
		String result = pascalCaseString.replaceAll("([a-z])([A-Z])", "$1 $2");
		result = result.substring(0, 1).toUpperCase() + result.substring(1);
		return result;
	}
}