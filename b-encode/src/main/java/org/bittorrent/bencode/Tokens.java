package org.bittorrent.bencode;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.util.Iterator;

import org.bittorrent.bencode.Token.TokenType;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

final class Tokens implements AutoCloseable, Closeable, Iterator<Token>, Iterable<Token> {
	
	private static final int BUFFER_SIZE = 2048;

	private final RandomAccessFile file;
	private final ByteChannel channel;
	private final ByteBuffer buffer;
	private int bytesRead = -1;

	public Tokens(String path) throws IOException {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(path), "path is empty");
		
		this.file = new RandomAccessFile(path, "r");
		this.channel = file.getChannel();
		this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
		fill();
	}

	public void close() throws IOException {
		file.close();
	}

	public boolean hasNext() {
		fillIfEmpty();
		return bytesRead != -1;
	}

	public Token next() {
		char c = read();
		TokenType type = TokenType.find(c);
		
		if (type == null) {
			throw new BEncodeRuntimeException("Invaled token: " + c);
		}
		
		switch (type) {
		case DICTIONARY:
		case LIST:
		case END:
			return new Token(type);

		case INTEGER:
			return integer();

		case STRING:
			return string(c);

		default:
			throw new BEncodeRuntimeException("Invaled token: " + c);
		}

	}

	public Iterator<Token> iterator() {
		return this;
	}
	
	private void fill() {
		buffer.clear();
		try {
			bytesRead = channel.read(buffer);
		} catch (IOException e) {
			throw new BEncodeRuntimeException(e);
		}
		buffer.flip();
	}

	private void fillIfEmpty() {
		if (!buffer.hasRemaining()) {
			fill();
		}
	}

	private char read() {
		fillIfEmpty();

		byte b = buffer.get();
		return ASCII.from(b);
	}

	private String read(final int count) {
		final byte[] result = new byte[count];

		int rem = count;
		int offset = 0;

		while (rem > 0) {
			int length = Math.min(rem, buffer.remaining());
			buffer.get(result, offset, length);
			offset += length - 1;
			rem -= length;

			fillIfEmpty();
		}

		return ASCII.toString(result);
	}

	private Token integer() {
		StringBuilder b = new StringBuilder();

		char c = read();

		while (c != 'e') {
			b.append(c);
			c = read();
		}

		return new Token(TokenType.INTEGER, b.toString());
	}

	private Token string(char ch) {
		StringBuilder b = new StringBuilder(ch + "");

		char c = read();

		while (!TokenType.DELIMER.has(c)) {
			b.append(c);
			c = read();
		}

		int length = Integer.parseInt(b.toString());
		return new Token(TokenType.STRING, read(length));
	}

}
