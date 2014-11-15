package org.bittorrent.bencode;

import static org.junit.Assert.*;

import org.bittorrent.bencode.Tokens;
import org.junit.Test;

@SuppressWarnings("unused")
public class TokensTest {

	private String path(String filename) {
		return getClass().getClassLoader().getResource("tokens/" + filename).getFile();
	}
	
	@Test
	public void generic1() throws Exception {
		int count = 0;
		Tokens stream = new  Tokens(path("file1.torrent"));
		for (Token token : stream) {
			count++;
		}
		stream.close();
		assertTrue(count > 0);
	}
	
	@Test
	public void generic2() throws Exception {
		int count = 0;
		Tokens stream = new  Tokens(path("file2.torrent"));
		for (Token token : stream) {
			count++;
		}
		stream.close();
		assertTrue(count > 0);
	}

	@Test
	public void singleToken() throws Exception {
		int count = 0;
		Tokens stream = new  Tokens(path("file3.torrent"));
		for (Token token : stream) {
			count++;
		}
		stream.close();
		assertEquals(1, count);
	}

	@Test
	public void twoTokens() throws Exception {
		int count = 0;
		Tokens stream = new  Tokens(path("file4.torrent"));
		for (Token token : stream) {
			count++;
		}
		stream.close();
		assertEquals(2, count);
	}

	@Test(expected = BEncodeRuntimeException.class)
	public void invalidFormat() throws Exception {
		Tokens stream = new  Tokens(path("file5.torrent"));
		for (Token token : stream);
		stream.close();
	}

	@Test(expected = BEncodeRuntimeException.class)
	public void invalidForamt2() throws Exception {		
		Tokens stream = new  Tokens(path("file6.torrent"));
		for (Token token : stream);
		stream.close();
	}

}
