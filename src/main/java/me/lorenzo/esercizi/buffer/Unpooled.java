package me.lorenzo.esercizi.buffer;

public class Unpooled {
    public static LoreBuf buffer() {
        return new SimpleLoreBuf();
    }

    public static LoreBuf fromArray(byte[] array) {
        return new SimpleLoreBuf(array);
    }
}
