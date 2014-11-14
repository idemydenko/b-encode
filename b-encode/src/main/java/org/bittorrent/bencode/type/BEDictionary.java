package org.bittorrent.bencode.type;

import java.util.Map;
import java.util.Objects;

public class BEDictionary extends BEType<Map<BEType<?>, BEType<?>>> {
	
	public static BEDictionaryBuilder builder() {
		return new BEDictionaryBuilder();
	}
	
	BEDictionary(Map<BEType<?>, BEType<?>> value) {
		super(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof BEDictionary)) {
			return false;
		}
		
		BEDictionary cast = (BEDictionary) obj;
		return Objects.equals(this.value, cast.value);
	}
}
