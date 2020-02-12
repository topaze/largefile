package fr.nohlan.open.largefile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LargeFileGenerator {

    private LargeFileGenerator() {
        // utility
    }

    public static void generate(final String filename, final long byteSizeInMb) {
        try(FileOutputStream fos = new FileOutputStream(new File(filename))) {

            final ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            for(int i=0; i<1023; i++) {
                baos.write("0".getBytes());
            }
            baos.write("\n".getBytes());
            for(long i=0; i<byteSizeInMb; i++) {
                fos.write(baos.toByteArray());
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        LargeFileGenerator.generate("target/tmp.txt", 1024l * 1024);

    }

}
