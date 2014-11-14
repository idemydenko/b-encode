package org.bittorrent.bencode;

import org.bittorrent.bencode.type.BEType;

final class Encoder {

	private final BEType<?> beObj;
	
	public Encoder(BEType<?> beObj) {
		this.beObj = beObj;
	}
	
	public byte[] encode() {
		return null;
	}
}
