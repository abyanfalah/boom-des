package id.asqi.idesa.bumdes.enums;

import java.util.HashMap;
import java.util.Map;

public class SiklusBayar {
	private static final int BULANAN = 1;
	private static final Map<Integer, String> SIKLUS_BAYAR_MAP = new HashMap<>();

	static {
		SIKLUS_BAYAR_MAP.put(BULANAN, "Bulanan");
	}

	public static String getString (int siklusBayar) {
		return SIKLUS_BAYAR_MAP.get(siklusBayar);
	}
}