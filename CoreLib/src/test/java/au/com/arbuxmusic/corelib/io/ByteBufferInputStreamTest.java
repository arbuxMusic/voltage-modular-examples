package au.com.arbuxmusic.corelib.io;

import au.com.arbuxmusic.corelib.util.arrays.CircularArrayList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ByteBufferInputStream Tests to ensure the stream works correctly
 *
 */
class ByteBufferInputStreamTest {

    /**
     * A null argument into the constructor should throw an exception
     */
    @Test
    void constructorNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            var stream = new ByteBufferInputStream(null);
        });
    }

    @Test
    void emptyByteBufferRead() throws IOException{
        byte[] expected = new byte[]{};
        ByteBuffer byteBuffer = ByteBuffer.wrap(expected);
        ByteBufferInputStream stream = new ByteBufferInputStream(byteBuffer);
        assert (stream.read() == -1);

    }

    @Test
    void singleByteBufferRead() throws IOException {
        byte[] expected = new byte[]{126};
        ByteBuffer byteBuffer = ByteBuffer.wrap(expected);
        ByteBufferInputStream stream = new ByteBufferInputStream(byteBuffer);
        assert (stream.read() == 126);
        assert (stream.read() == -1);
    }


    @Test
    void emptyByteBufferReadArr() {
        byte[] expected = new byte[]{};
        ByteBuffer byteBuffer = ByteBuffer.wrap(expected);
        ByteBufferInputStream stream = new ByteBufferInputStream(byteBuffer);
        var actualArr = new byte[expected.length];
        assert (stream.read(actualArr, 0, actualArr.length) == -1);
    }

    @Test
    void singleByteBufferReadArr() {
        byte[] expected = new byte[]{125};
        ByteBuffer byteBuffer = ByteBuffer.wrap(expected);
        ByteBufferInputStream stream = new ByteBufferInputStream(byteBuffer);

        var actualArr = new byte[expected.length];
        assert (stream.read(actualArr, 0, actualArr.length) == expected.length);

        for(int i = 0; i < expected.length; i++){
            assertEquals(expected[i], actualArr[i]);
        }
        assert (stream.read(actualArr, 0, actualArr.length) == -1);

    }


    @Test
    void tenByteBufferPartialReadArr() {
        byte[] expected = new byte[]{1,2,3,4,5,6,7,8,9,10};
        ByteBuffer byteBuffer = ByteBuffer.wrap(expected);
        ByteBufferInputStream stream = new ByteBufferInputStream(byteBuffer);

        var actualArr = new byte[3];
        int actualLength;
        for (int j = 0; j <=3; j++) {
            actualLength = stream.read(actualArr, 0, actualArr.length);
            assertEquals((j == 3 ? 1 : 3), actualLength);
            for (int i = 0; i < actualLength; i++) {
                assertEquals(expected[i] + j * actualArr.length, actualArr[i]);
            }
        }
        assert (stream.read(actualArr, 0, actualArr.length) == -1);

    }

}