package me.lorenzo.esercizi.buffer;

public interface LoreBuf {
    void writeByte(byte b);

    byte readByte(byte b);

    void writeInt(int i);

    int readInt();

    void writeChar(char c);

    char readChar();

    void writeString(String string);

    String readString();

    void reset();

    int getSize();

    void writeTo(byte[] buffer);
}
