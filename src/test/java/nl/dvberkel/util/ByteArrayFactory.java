package nl.dvberkel.util;

public class ByteArrayFactory {
    public static ByteArrayFactory ofLength(int length) {
        return new ByteArrayFactory(length);
    }

    private final int length;

    public ByteArrayFactory(int cellSize) {
        this.length = cellSize;
    }

    public byte[] withContent(int... content) {
        byte[] result = new byte[length];
        for (int index = 0; index < content.length && index < length; index++) {
            result[index] = (byte) content[index];
        }
        return result;
    }

    public byte[] withContentInReverse(int... content) {
        byte[] result = new byte[length];
        for (int index = 0; index < content.length && index < length; index++) {
            result[length - 1 - index] = (byte) content[index];
        }
        return result;
    }
}
