package org.bittorrent.bencode;

import java.nio.charset.Charset;

public final class ASCII {
	
	static final String CHARSET_NAME = "US-ASCII";
	static final Charset CHARSET = Charset.forName(CHARSET_NAME);

	private ASCII() {
	}

	public static byte to(char c) {
		return (byte) c;
	}

	public static char from(int c) {
		return (char) c;
	}
	
	public static char from(byte c) {
		return (char) c;
	}
	
	public static int toInt(byte[] bytes) {
		return Integer.parseInt(new String(bytes, CHARSET));
	}

	public static long toLong(byte[] bytes) {
		return Long.parseLong(new String(bytes, CHARSET));
	}

	public static String toString(byte[] bytes) {
		return new String(bytes, CHARSET);
	}
	
	public static byte[] bytes(String str) {
		return str.getBytes(CHARSET);
	}
}
