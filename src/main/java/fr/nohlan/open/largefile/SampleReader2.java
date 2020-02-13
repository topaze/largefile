package fr.nohlan.open.largefile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class SampleReader2 {

    public static void main(final String[] args) throws IOException {

        try (
                RandomAccessFile reader = new RandomAccessFile("target/tmp.txt", "r");
                FileChannel channel = reader.getChannel();
                ) {

            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            final ByteBuffer buff = ByteBuffer.allocate(bufferSize);

            long fileContentLength = 0;
            while (channel.read(buff) > 0) {
                fileContentLength = fileContentLength + buff.position();
                buff.clear();
            }

            final long sz = fileContentLength;
            System.out.format("%s B%n", sz);
            System.out.format("%s KB%n", sz/1024);
            System.out.format("%s MB%n", sz/1024/1024);
            System.out.format("%s GB%n", sz/1024/1024/1024);

        }

    }

}

