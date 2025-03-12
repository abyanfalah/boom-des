package id.asqi.idesa.bumdes.enums;

import java.util.HashMap;
import java.util.Map;

public class SatuanDenda {
	public static final int RUPIAH = 1;
	public static final int PERSENTASE = 2;

	private static final Map<Integer, String> SATUAN_DENDA_MAP = new HashMap<>();

	static {
		SATUAN_DENDA_MAP.put(RUPIAH, "Rupiah");
		SATUAN_DENDA_MAP.put(PERSENTASE, "Persentase");
	}

	public static String getString(int satuanDenda) { return SATUAN_DENDA_MAP.get(satuanDenda);}
}