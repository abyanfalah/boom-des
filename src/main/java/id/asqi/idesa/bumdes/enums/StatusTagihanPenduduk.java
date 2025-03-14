package id.asqi.idesa.bumdes.enums;

import java.util.HashMap;
import java.util.Map;

public class StatusTagihanPenduduk {
	public static final int BELUM_LUNAS = 0;
	public static final int LUNAS = 1;

	private static final Map<Integer, String>  STATUS_TAGIHAN_MAP = new HashMap<>();

	static {
		STATUS_TAGIHAN_MAP.put(BELUM_LUNAS, "Belum Lunas");
		STATUS_TAGIHAN_MAP.put(LUNAS, "Lunas");
	}

	public static String getString(int statusTagihan){return STATUS_TAGIHAN_MAP.get(statusTagihan); }
}