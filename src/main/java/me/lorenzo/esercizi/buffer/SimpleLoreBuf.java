package me.lorenzo.esercizi.buffer;

import me.lorenzo.esercizi.utils.BufferUtil;

import java.nio.ByteBuffer;

public class SimpleLoreBuf implements LoreBuf {
    private ByteBuffer byteBuffer;
    private int size;
    private int readerIndex;

    SimpleLoreBuf() {
        this.byteBuffer = ByteBuffer.allocate(4);
        this.size = 4;
        this.readerIndex = 0;

        byteBuffer.putInt(0, size);
    }

    SimpleLoreBuf(byte[] array) {
        this.byteBuffer = ByteBuffer.allocate(array.length);
        this.size = array.length;
        this.readerIndex = 0;

        byteBuffer.put(array);
    }

    @Override
    public void writeByte(byte b) {
        checkAndAlloc(1);
        byteBuffer.put(b);
    }

    @Override
    public byte readByte(byte b) {
        byte value = byteBuffer.get(readerIndex);
        readerIndex += 1;

        return value;
    }

    @Override
    public void writeInt(int i) {
        checkAndAlloc(4);
        byteBuffer.putInt(i);
    }

    @Override
    public int readInt() {
        int value = byteBuffer.getInt(readerIndex);
        readerIndex += 4;

        return value;
    }

    @Override
    public void writeChar(char c) {
        checkAndAlloc(2);
        byteBuffer.putChar(c);
    }

    @Override
    public char readChar() {
        char value = byteBuffer.getChar(readerIndex);
        readerIndex += 2;

        return value;
    }

    @Override
    public void writeString(String string) {
        byte[] value = string.getBytes();
        int length = value.length;

        writeInt(length);

        checkAndAlloc(length);
        byteBuffer.put(value);
    }

    @Override
    public String readString() {
        int length = readInt();
        char[] value = new char[length];

        for (int i = 0; i < length; i++) {
            value[i] = (char) byteBuffer.get(readerIndex + i);
        }

        readerIndex += (4 + length);

        return new String(value);
    }

    @Override
    public void reset() {
        this.byteBuffer.reset();
        this.size = 0;
        this.readerIndex = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public byte[] getBytes() {
        byte[] buffer = new byte[size];
        System.arraycopy(byteBuffer.array(), 0, buffer, 0, size);
        return buffer;
    }

    private void checkAndAlloc(int size) {
        this.size += size;
        if (this.size > this.byteBuffer.capacity()) {
            this.byteBuffer = BufferUtil.increaseCapacity(byteBuffer, size);
        }
    }
}
