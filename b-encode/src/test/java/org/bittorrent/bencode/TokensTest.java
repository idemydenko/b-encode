package org.bittorrent.bencode;

import static org.junit.Assert.*;

import org.bittorrent.bencode.Tokens;
import org.junit.Test;

public class TokensTest {

	private String path(String filename) {
		return getClass().getClassLoader().getResource(filename).getFile();
	}
	
	@Test
	public void file1() throws Exception {
		int count = 0;
		Tokens stream = new  Tokens(path("file1.torrent"));
		for (Token token : stream) {
			count++;
		}
		stream.close();
		assertTrue(count > 0);
	}
	
	@Test
	public void file2() throws Exception {
		int count = 0;
		Tokens stream = new  Tokens(path("file2.torrent"));
		for (Token token : stream) {
			count++;
		}
		stream.close();
		assertTrue(count > 0);
	}

	@Test
	public void file3() throws Exception {
		int count = 0;
		Tokens stream = new  Tokens(path("file3.torrent"));
		for (Token token : stream) {
			count++;
		}
		stream.close();
		assertEquals(1, count);
	}

	@Test
	public void file4() throws Exception {
		int count = 0;
		Tokens stream = new  Tokens(path("file4.torrent"));
		for (Token token : stream) {
			count++;
		}
		stream.close();
		assertEquals(2, count);
	}

}
