package org.bittorrent.bencode.type;

import java.util.Objects;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class BEInteger extends BEType<Long> {
	
	public static BEInteger create(String string) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(string), "string is empty");
		Preconditions.checkArgument(string.length() == 1 || string.charAt(0) != '0');
		
		Long value = Long.parseLong(string);
		return new BEInteger(value);
	}
	
	/*package*/ BEInteger(Long value) {
		super(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof BEInteger)) {
			return false;
		}
		
		BEInteger cast = (BEInteger) obj;
		return Objects.equals(this.value, cast.value);
	}
	
}
