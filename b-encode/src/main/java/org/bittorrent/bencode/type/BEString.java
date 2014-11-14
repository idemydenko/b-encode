package org.bittorrent.bencode.type;

import java.util.Objects;


public class BEString extends BEType<String> {
	
	public static BEString create(String string) {
		return new BEString(string);
	}
	
	/*package*/ BEString(String value) {
		super(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof BEString)) {
			return false;
		}
		
		BEString cast = (BEString) obj;
		return Objects.equals(this.value, cast.value);
	}
	
	@Override
	public String toString() {
		final int limit = 100;
		return value.length() > limit ? value.substring(0, limit) : value;
	}
}
