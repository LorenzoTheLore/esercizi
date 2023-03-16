package me.lorenzo.esercizi.utils;

import java.nio.ByteBuffer;

public class BufferUtil {
    public static ByteBuffer increaseCapacity(ByteBuffer buffer, int size)
            throws IllegalArgumentException {
        if (buffer == null)
            throw new IllegalArgumentException("buffer is null");
        if (size < 0)
            throw new IllegalArgumentException("size less than 0");

        int capacity = buffer.capacity() + size;
        ByteBuffer result = allocate(capacity, buffer.isDirect());
        buffer.flip();
        result.put(buffer);
        return result;
    }

    public static ByteBuffer allocate(int capacity, boolean direct)
            throws IllegalArgumentException {
        if (capacity < 0)
            throw new IllegalArgumentException("capacity can't be negative");
        return direct ? ByteBuffer.allocateDirect(capacity) : ByteBuffer
                .allocate(capacity);
    }
}
