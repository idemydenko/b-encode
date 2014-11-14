package org.bittorrent.bencode;

import java.util.ArrayDeque;
import java.util.Deque;

import org.bittorrent.bencode.type.BEDictionary;
import org.bittorrent.bencode.type.BEInteger;
import org.bittorrent.bencode.type.BEList;
import org.bittorrent.bencode.type.BEString;
import org.bittorrent.bencode.type.BEType;
import org.bittorrent.bencode.type.BETypeBuilder;

import com.google.common.base.Preconditions;

class Decoder {
	private final Tokens tokens;
	private final Deque<BETypeBuilder<? extends BEType<?>>> deque = new ArrayDeque<BETypeBuilder<? extends BEType<?>>>();
	private BETypeBuilder<? extends BEType<?>> head;
	private boolean done = false;
	
	Decoder(Tokens tokens) {
		Preconditions.checkNotNull(tokens, "tokens are null");
		this.tokens = tokens;
	}
	
	public BEType<?> decode() {
		BEType<?> result = null;
		
		for (Token token : tokens) {
			switch (token.getType()) {
			case STRING:
				BEString string = BEString.create(token.getValue());
				if (head != null) {
					head.add(string);
				} else {
					result = string;
					done = true;
				}
				break;
				
			case INTEGER:
				BEInteger integer = BEInteger.create(token.getValue());
				if (head != null) {
					head.add(integer);
				} else {
					result = integer;
					done = true;
				}
				break;

			case LIST:
				push(BEList.builder());
				break;

			case DICTIONARY:
				push(BEDictionary.builder());
				break;

			case END:
				
				BETypeBuilder<? extends BEType<?>> builder = pop();
				
				BEType<?> value = builder.build();
				
				if (head != null) {
					head.add(value);
				} else {
					result = value;
					done = true;
				}
				break;

			default:
				throw new BEncodeRuntimeException("invaled token: " + token);  
			}
		}
		
		return result;
	}
	
	private void push(BETypeBuilder<? extends BEType<?>> builder) {
		deque.offerFirst(builder);
		head = deque.peekFirst();
	}
	
	private BETypeBuilder<? extends BEType<?>> pop() {
		BETypeBuilder<? extends BEType<?>> result = deque.pollFirst();
		head = deque.peekFirst();
		return result;
	}
}
