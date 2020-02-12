package fr.nohlan.open.largefile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

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

            final String fileContent = new String(out.toByteArray(), StandardCharsets.UTF_8);
            System.out.format("%s B%n", fileContent.length());
            System.out.format("%s KB%n", fileContent.length()/1024);
            System.out.format("%s MB%n", fileContent.length()/1024/1024);
            System.out.format("%s GB%n", fileContent.length()/1024/1024/1024);

        }

    }

}

