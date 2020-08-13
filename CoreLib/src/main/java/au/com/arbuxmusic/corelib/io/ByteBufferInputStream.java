package au.com.arbuxmusic.corelib.io;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.io.IOException;

// Enable a ByteBuffer to be read in as an InputStream to work with ImageIO.
public class ByteBufferInputStream extends InputStream {
	ByteBuffer buffer;
	
	// Constructor
	public ByteBufferInputStream(ByteBuffer buffer) {
		this.buffer = buffer;
	}
	
	// read the next value
	// throws IOException as part of the underlying interface
	public synchronized int read() throws IOException {
		if (buffer == null) {
			throw new IOException();
		}
		
		// If there are no entries left, 
		if (!buffer.hasRemaining()) {
			return -1;
		}
		return buffer.get();
	}
	
	// read into provided array
	public synchronized int read(byte[] b, int off, int len) {
		// if there are no entries left, let the caller know
		if (!buffer.hasRemaining()) {
			return -1;
		}
		else if (len > buffer.remaining())
		{
			// don't try to read beyond the length of the buffer
			len = buffer.remaining();
		}
		
		// read in the bytes
		buffer.get(b, off, len);
		return len;
	}
	
}