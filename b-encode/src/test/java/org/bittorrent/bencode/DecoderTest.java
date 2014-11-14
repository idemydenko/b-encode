package org.bittorrent.bencode;

import org.junit.Test;

import static org.junit.Assert.*;

public class DecoderTest {

	private String path(String filename) {
		return getClass().getClassLoader().getResource(filename).getFile();
	}
	
	@Test
	public void file1() throws Exception {
		Tokens stream = new  Tokens(path("file1.torrent"));

		Decoder decoder = new Decoder(stream);
		assertNotNull(decoder.decode());
		
		stream.close();
	}
	
	@Test
	public void file2() throws Exception {
		Tokens stream = new  Tokens(path("file2.torrent"));
		
		Decoder decoder = new Decoder(stream);
		assertNotNull(decoder.decode());

		stream.close();
	}
	
	@Test
	public void file3() throws Exception {
		Tokens stream = new  Tokens(path("file3.torrent"));
		
		Decoder decoder = new Decoder(stream);
		assertNotNull(decoder.decode());

		stream.close();
	}

	@Test
	public void file4() throws Exception {
		Tokens stream = new  Tokens(path("file4.torrent"));
		
		Decoder decoder = new Decoder(stream);
		assertNotNull(decoder.decode());

		stream.close();
	}

}
