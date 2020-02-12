package fr.nohlan.open.largefile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class SampleWriter {

    public static void main(final String[] args) throws IOException {

        final String file = "target/tmp.txt";
        try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw");
                FileChannel channel = randomFile.getChannel()){
            channel.position(16);
            final ByteBuffer buff = ByteBuffer.wrap("123456789A".getBytes(StandardCharsets.UTF_8));
            channel.write(buff);
        }

    }

}

