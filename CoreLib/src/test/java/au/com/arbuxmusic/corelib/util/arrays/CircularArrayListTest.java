package au.com.arbuxmusic.corelib.util.arrays;

import static org.junit.jupiter.api.Assertions.*;

class CircularArrayListTest {

    /**
     * A negative size parameter should cause an exception
     */
    @org.junit.jupiter.api.Test
    void constructorNegativeSizeString() {
        assertThrows(IllegalArgumentException.class, () -> {
            var list = new CircularArrayList<String>(-1, "");
            });
    }

    /**
     * A size parameter of zero should cause an exception
     */
    @org.junit.jupiter.api.Test
    void constructorZeroSizeString() {
        assertThrows(IllegalArgumentException.class, () -> {
            var list = new CircularArrayList<String>(0, "");
        });
    }

    /**
     * A unit size parameter of zero should cause an exception
     */
    @org.junit.jupiter.api.Test
    void constructorUnitSizeString() {
        var list = new CircularArrayList<String>(1, "");
        assert(list.size() == 1);
    }

    /**
     * A unit size parameter of zero should cause an exception
     */
    @org.junit.jupiter.api.Test
    void constructor128SizeString() {
        var list = new CircularArrayList<String>(128, "");
        assert(list.size() == 128);
    }

    /**
     * Add a single item to the array
     */
    @org.junit.jupiter.api.Test
    void putSingleItemInSingleItemArray() {
        String[] expected = new String[] {"Test 1"};
        var list = new CircularArrayList<String>(1, "");
        for(String item : expected) {
            list.put(item);
        }
        String[] actual = new String[expected.length];
        list.toArray(actual);
        assertArrayEquals(expected, actual);
    }


    /**
     * Add a two items to a single item array
     */
    @org.junit.jupiter.api.Test
    void putTwoItemsInSingleItemArray() {
        String[] expected = new String[] {"Test 1", "Test 2"};
        var list = new CircularArrayList<String>(1, "");
        for(String item : expected) {
            list.put(item);
        }
        String[] actual = new String[expected.length];
        list.toArray(actual);
        assertEquals(expected[1], actual[0]);
    }

    /**
     * Add a single item to the array
     */
    @org.junit.jupiter.api.Test
    void putItemsInTenItemArray() {
        String[] expected = new String[] {"Test 1","Test 2","Test 3","Test 4","Test 5","Test 6","Test 7","Test 8","Test 9","Test 10"};
        var list = new CircularArrayList<String>(expected.length, "");
        for(String item : expected) {
            list.put(item);
        }
        String[] actual = new String[expected.length];
        list.toArray(actual);
        assertArrayEquals(expected, actual);
    }

    /**
     * Add a single item to the array
     */
    @org.junit.jupiter.api.Test
    void putItemsInTenItemIn5ItemArray() {
        String[] expected = new String[]{"Test 1", "Test 2", "Test 3", "Test 4", "Test 5", "Test 6", "Test 7", "Test 8", "Test 9", "Test 10"};
        var list = new CircularArrayList<String>(5, "");
        for (String item : expected) {
            list.put(item);
        }
        String[] actual = new String[5];
        list.toArray(actual);
        for (int i = 0; i < actual.length; i++)
            assertEquals(expected[i + 5], actual[i]);
    }

    /**
     * Reset the list back to default values
     */
    @org.junit.jupiter.api.Test
    void reset() {
        String defaultValue = "";
        var list = new CircularArrayList<String>(5, defaultValue);
        list.reset();
        String[] actual = new String[list.size()];
        list.toArray(actual);
        for (int i = 0; i < list.size(); i++){
            assertEquals(defaultValue, actual[i]);
        }
    }
}