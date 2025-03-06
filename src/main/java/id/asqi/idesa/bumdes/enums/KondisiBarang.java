package id.asqi.idesa.bumdes.enums;

import java.util.HashMap;
import java.util.Map;

public class KondisiBarang {
	public static final int BARU = 1;
	public static final int BEKAS = 2;

	private static final Map<Integer, String> KONDISI_BARANG_MAP = new HashMap<>();

	static {
		KONDISI_BARANG_MAP.put(BARU, "Baru");
		KONDISI_BARANG_MAP.put(BEKAS, "Bekas");
	}

	public static String getString(int kondisiBarang) {
		return KONDISI_BARANG_MAP.get(kondisiBarang);
	}
}