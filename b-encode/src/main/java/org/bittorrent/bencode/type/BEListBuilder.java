package org.bittorrent.bencode.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.google.common.base.Preconditions;

public class BEListBuilder implements BETypeBuilder<BEList> {
	private final List<BEType<?>> list = new ArrayList<BEType<?>>();
	private boolean completed = false;
	
	BEListBuilder() {
	}
	
	public BEListBuilder add(BEType<?> value) {
		Preconditions.checkState(!completed, "list building completed: " + list);
		
		list.add(Objects.requireNonNull(value, "value is null"));
		
		return this;
	}
	
	public BEList build() {
		Preconditions.checkState(!list.isEmpty(), "list is empty");
		
		completed = true;
		return new BEList(Collections.unmodifiableList(list));
	}
	
	@Override
	public String toString() {
		return "4list: " + list;
	}
}