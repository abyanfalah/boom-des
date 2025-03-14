package id.asqi.idesa.bumdes.core.service;

import id.asqi.idesa.bumdes.core.auth.Auth;
import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;
import id.asqi.idesa.bumdes.model.Penduduk;

import java.time.LocalDateTime;
import java.util.Objects;

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

	public static void sameDesaCheck(Penduduk p){
		if(! Objects.equals(p.getAlamatDesa().getId(), Auth.getAlamatDesaId())) throw new InvalidOperationException("Penduduk ini bukan penduduk desa anda!");
	}

	private static String pascalToSentenceCase (String pascalCaseString) {
		String result = pascalCaseString.replaceAll("([a-z])([A-Z])", "$1 $2");
		result = result.substring(0, 1).toUpperCase() + result.substring(1);
		return result;
	}
}