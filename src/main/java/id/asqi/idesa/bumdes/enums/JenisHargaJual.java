package id.asqi.idesa.bumdes.enums;

import java.util.HashMap;
import java.util.Map;

public class JenisHargaJual {
	public static final int DEFAULT = 1;
	public static final int HARGA_GROSIR = 2;
	public static final int HARGA_DISKON = 3;

	private static final Map<Integer, String> JENIS_HARGA_JUAL_MAP = new HashMap<>();

	static {
		JENIS_HARGA_JUAL_MAP.put(DEFAULT, "Default");
		JENIS_HARGA_JUAL_MAP.put(HARGA_GROSIR, "Harga Grosir");
		JENIS_HARGA_JUAL_MAP.put(HARGA_DISKON, "Harga Diskon");
	}

	public static String getString(int jenisHargaJual) {
		return JENIS_HARGA_JUAL_MAP.get(jenisHargaJual);
	}
}