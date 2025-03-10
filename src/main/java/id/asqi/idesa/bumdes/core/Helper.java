package id.asqi.idesa.bumdes.core;

import id.asqi.idesa.bumdes.core.component.exception.InvalidOperationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class Helper {
	public static Character getBooleanChar (Boolean bool) {
		return bool ? '1' : '0';
	}

	public static Integer nullToZero (Integer value) {
		return value == null ? 0 : value;
	}

	public static Long nullToZero (Long value) {
		return value == null ? 0L : value;
	}


	public static String indonesianMonth (Integer monthNum) {
		Map<Integer, String> monthMap = new HashMap<>();
		monthMap.put(1, "Januari");
		monthMap.put(2, "Februari");
		monthMap.put(3, "Maret");
		monthMap.put(4, "April");
		monthMap.put(5, "Mei");
		monthMap.put(6, "Juni");
		monthMap.put(7, "Juli");
		monthMap.put(8, "Agustus");
		monthMap.put(9, "September");
		monthMap.put(10, "Oktober");
		monthMap.put(11, "November");
		monthMap.put(12, "Desember");
		return monthMap.get(monthNum);
	}

	public static String getMainPackageString (String packageName) {
		int thirdDotIndex = packageName.indexOf('.', packageName.indexOf('.', packageName.indexOf('.') + 1) + 1);
		if (thirdDotIndex != - 1) {
			packageName = packageName.substring(0, thirdDotIndex);
		}
		return packageName;
	}

	public static LocalDateTime getYearLocalDateTime (String yearString) {
		yearString = yearString + "-01-01";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate year = LocalDate.parse(yearString, formatter);
		return year.atStartOfDay();
	}

	public static LocalDateTime getStartOfYear (LocalDateTime ldt) {
		LocalDate startOfYear = ldt.with(TemporalAdjusters.firstDayOfYear()).toLocalDate();
		return startOfYear.atStartOfDay();
	}

	public static LocalDateTime getEndOfYear (LocalDateTime ldt) {
		return getStartOfYear(ldt).plusYears(1L).minusSeconds(1L);
	}

	public static Integer getCurrentYear () {
		return LocalDateTime.now().getYear();
	}

	public static Integer getCurrentMonthValue () {
		return LocalDateTime.now().getMonthValue();
	}


	public static <T, DTO> Page<DTO> customContentPage (Page<T> page, List<DTO> dtoList) {
		return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
	}


	//	private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMBERS = "0123456789";

	public static String generateRandomString (int length, boolean includeLetters, boolean includeNumbers) {
		if (length <= 0 || (! includeLetters && ! includeNumbers)) {
			throw new IllegalArgumentException("Invalid parameters");
		}

		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		String characters = "";
		if (includeLetters) {
			characters += LETTERS;
		}
		if (includeNumbers) {
			characters += NUMBERS;
		}

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characters.length());
			sb.append(characters.charAt(randomIndex));
		}

		return sb.toString();
	}

	public static String getMonthName (int monthNumber) {
		if (monthNumber < 1 || monthNumber > 12) {
			throw new IllegalArgumentException("Invalid month number: " + monthNumber);
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, monthNumber - 1);
		return cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
	}

	public static String getMonthNameIndonesian (int monthNumber) {
		if (monthNumber < 1 || monthNumber > 12) {
			throw new IllegalArgumentException("Invalid month number: " + monthNumber);
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, monthNumber - 1);
		return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale.Builder().setLanguage("in").setRegion("ID").build());
	}

	public static void printFields(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		String[] names = Arrays.stream(fields).map(Field::getName).toArray(String[]::new);
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				System.out.println("field name  : " + field.getName());
				System.out.println("field value : " + field.get(obj));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}


}