package org.bittorrent.bencode.type;

public interface BETypeBuilder<T extends BEType<?>> {

	BETypeBuilder<T> add(BEType<?> value);
	
	T build();
}
