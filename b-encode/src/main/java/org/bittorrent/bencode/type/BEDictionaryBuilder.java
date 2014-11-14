package org.bittorrent.bencode.type;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.common.base.Preconditions;

public class BEDictionaryBuilder implements BETypeBuilder<BEDictionary>{
	
	private boolean completed = false;
	private final Map<BEType<?>, BEType<?>> map = new HashMap<BEType<?>, BEType<?>>();
	private BEType<?> key;
	
	BEDictionaryBuilder() {
	}

	public BEDictionaryBuilder key(BEType<?> key) {
		Preconditions.checkState(!completed, "building completed");
		Preconditions.checkState(this.key == null, "key already defined");
		
		this.key = Objects.requireNonNull(key, "key is null");
		
		return this;
	}

	@Override
	public BETypeBuilder<BEDictionary> add(BEType<?> value) {
		return key == null ? key(value) : value(value);
	}
	
	public BEDictionaryBuilder value(BEType<?> value) {
		Preconditions.checkState(!completed, "dict building completed: " + map);
		Preconditions.checkState(this.key != null, "key not defined");
		
		map.put(key, Objects.requireNonNull(value, "value is null"));
		key = null;
		
		return this;
	}

	public BEDictionary build() {
		Preconditions.checkState(!map.isEmpty(), "map is empty");
		Preconditions.checkState(this.key == null, "key already defined");

		completed = true;
		return new BEDictionary(Collections.unmodifiableMap(map));
	}
	
	@Override
	public String toString() {
		return "4dict (" + key + "): " + map;
	}
}