package org.bittorrent.bencode;

public final class BEncodeRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public BEncodeRuntimeException() {
	}

	public BEncodeRuntimeException(Throwable e) {
		super(e);
	}

	public BEncodeRuntimeException(String msg) {
		super(msg);
	}
}
