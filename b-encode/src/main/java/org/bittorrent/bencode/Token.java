package org.bittorrent.bencode;

import java.util.Arrays;
import java.util.Objects;

import com.google.common.base.Preconditions;

class Token {

	public enum TokenType {
		STRING('5', '6', '7', '8', '9', '1', '2', '3', '4'),
		INTEGER('i'),
		LIST('l'),
		DICTIONARY('d'),
		END('e'),
		DELIMER(':');
		
		private final char[] specs;
		private final boolean composite;
		
		private TokenType(char... specs) {
			Objects.requireNonNull(specs, "specs is null");
			Preconditions.checkArgument(specs.length > 0, "specs is empty");
			
			this.composite = specs.length > 1;
			
			if (composite) {
				Arrays.sort(specs);
			}
			
			this.specs = specs;
		}
		
		public boolean has(char c) {
			return composite ? Arrays.binarySearch(specs, c) >= 0 : specs[0] == c;
		}
		
		public char spec() {
			if (composite) {
				throw new UnsupportedOperationException();
			}
			return specs[0];
		}

		public String specToString() {
			return Character.toString(spec());
		}

		public static TokenType find(char c) {
			for (TokenType type : values()) {
				if (type.has(c)) {
					return type;
				}
			}
			
			return null;
		}
 	}

	public static final String SPECIFIER = "SPECIFIER";
	
	private final TokenType type;
	private final String value;
	
	Token(Token.TokenType type, String value) {
		this.type = type;
		this.value = value;
	}

	Token(Token.TokenType type) {
		this(type, type.specToString());
	}

	public Token.TokenType getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Token)) {
			return false;
		}
		
		Token other = (Token) obj;
		
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		
		if (type != other.type) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final int limit = 100;
		return type + ": " + (value.length() > limit ? value.substring(0, limit) : value);
	}
	
}