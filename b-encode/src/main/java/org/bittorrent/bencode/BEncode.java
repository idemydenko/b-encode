package org.bittorrent.bencode;

import java.io.IOException;

import org.bittorrent.bencode.type.BEType;

import com.google.common.io.Closeables;

public final class BEncode {

	public BEType<?> deserialize(String path) throws IOException {
		Tokens tokens = null;

		try {
			tokens = new Tokens(path);
			return new Decoder(tokens).decode();
		} finally {
			Closeables.close(tokens, true);
		}
	}

	public byte[] serialize(BEType<?> beObj) {
		return new Encoder(beObj).encode();
	}
}
