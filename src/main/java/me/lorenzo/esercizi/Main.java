package me.lorenzo.esercizi;

import me.lorenzo.esercizi.buffer.LoreBuf;
import me.lorenzo.esercizi.buffer.Unpooled;

public class Main {
    public static void main(String[] args) {
        LoreBuf loreBuf = Unpooled.buffer();
        loreBuf.writeInt(51);
        loreBuf.writeChar('B');
        loreBuf.writeChar('L');

        byte[] array = loreBuf.getBytes();
        LoreBuf copied = Unpooled.fromArray(array);
        System.out.println(copied.readInt());
        System.out.println(copied.readChar());
        System.out.println(copied.readChar());
    }
}