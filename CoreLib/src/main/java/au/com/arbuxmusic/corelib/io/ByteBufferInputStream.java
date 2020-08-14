package au.com.arbuxmusic.corelib.io;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.io.IOException;

/**
 * Enable a ByteBuffer to be read in as an InputStream to work with ImageIO.
 */
public class ByteBufferInputStream extends InputStream {

	/**
	 * The source byte buffer to read from
	 */
	ByteBuffer buffer;

	/**
	 * Constructor
	 * @param buffer contains the base buffer for access by the InputStream
	 */
	public ByteBufferInputStream(ByteBuffer buffer) {
		if (buffer == null)
			throw new IllegalArgumentException("buffer cannot be null");
		this.buffer = buffer;
	}

	/**
	 * read the next value
	 * @return the next value in the stream
	 * @throws IOException is thrown if buffer is null
	 */
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

	/**
	 * read into provided array
	 * @param b the array to put the range of values into
	 * @param off the offset to start writing from the source
	 * @param len the number of bytes to read into the output array
	 * @return the number of values read
	 */
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