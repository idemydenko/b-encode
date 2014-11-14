package org.bittorrent.bencode.type;

import java.util.Objects;

public abstract class BEType<V> {
	
	public enum Type {
		BE_INTEGER,
		BE_STRING,
		BE_LIST,
		BE_DICTIONARY;
	}
	
	protected final V value;
	
	protected BEType(V value) {
		this.value = Objects.requireNonNull(value, "value is null");
	}
	
	public final V getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
