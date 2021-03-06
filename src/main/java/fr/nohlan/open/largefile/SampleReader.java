package fr.nohlan.open.largefile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class SampleReader {

    public static void main(final String[] args) throws IOException {

        try (
                RandomAccessFile reader = new RandomAccessFile("target/tmp.txt", "r");
                FileChannel channel = reader.getChannel();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            final ByteBuffer buff = ByteBuffer.allocate(bufferSize);

            while (channel.read(buff) > 0) {
                out.write(buff.array(), 0, buff.position());
                buff.clear();
            }

            // final String fileContent = new String(out.toByteArray(), StandardCharsets.UTF_8);
            final long sz = out.size();
            System.out.format("%s B%n", sz);
            System.out.format("%s KB%n", sz/1024);
            System.out.format("%s MB%n", sz/1024/1024);
            System.out.format("%s GB%n", sz/1024/1024/1024);

        }

    }

}

