package org.bittorrent.bencode.type;

import java.util.List;
import java.util.Objects;

public class BEList extends BEType<List<BEType<?>>> {
	
	public static BEListBuilder builder() {
		return new BEListBuilder();
	}
	
	public BEList(List<BEType<?>> list) {
		super(list);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof BEList)) {
			return false;
		}
		
		BEList cast = (BEList) obj;
		return Objects.equals(this.value, cast.value);
	}
}
