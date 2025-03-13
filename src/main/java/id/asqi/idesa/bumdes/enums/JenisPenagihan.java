package id.asqi.idesa.bumdes.enums;

import java.util.HashMap;
import java.util.Map;

public class JenisPenagihan {
	public static final int BULANAN = 1;
	public static final int PER_PEMAKAIAN = 2;

	private static final Map<Integer, String> JENIS_PENAGIHAN_MAP = new HashMap<>();

	static {
		JENIS_PENAGIHAN_MAP.put(BULANAN, "Bulanan");
		JENIS_PENAGIHAN_MAP.put(PER_PEMAKAIAN, "Per-pemakaian");
	}

	public static String getString(int jenisPenagihan) {
		return JENIS_PENAGIHAN_MAP.get(jenisPenagihan);
	}
}