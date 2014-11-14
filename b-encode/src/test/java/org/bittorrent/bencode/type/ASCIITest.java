package org.bittorrent.bencode.type;

import static org.junit.Assert.*;

import org.bittorrent.bencode.ASCII;
import org.junit.Test;

public class ASCIITest {
	
	@Test
	public void toASCII() throws Exception {
		assertEquals(48, ASCII.to('0'));
		assertEquals(57, ASCII.to('9'));
		assertEquals(65, ASCII.to('A'));
		assertEquals(90, ASCII.to('Z'));
		assertEquals(97, ASCII.to('a'));
		assertEquals(122, ASCII.to('z'));
	}
	
	@Test
	public void fromASCII() throws Exception {
		assertEquals(ASCII.from((byte)48), '0');
		assertEquals(ASCII.from(57), '9');
		assertEquals(ASCII.from((byte)65), 'A');
		assertEquals(ASCII.from(90), 'Z');
		assertEquals(ASCII.from((byte)97), 'a');
		assertEquals(ASCII.from(122), 'z');
	}

	@Test
	public void parseInte() throws Exception {
		byte[] b123 = new byte[] { 49, 50, 51 };		
		assertEquals(123, ASCII.toInt(b123));
		
		byte[] minus23 = new byte[] { 45, 50, 51 };		
		assertEquals(-23, ASCII.toInt(minus23));

	}
}
