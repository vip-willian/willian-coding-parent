package cn.willian.coding.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-11 17:54:18
 */
public class FileReadWrite {

    public static void main(String[] args) {
        FileReadWrite.toByteArray();
    }

    public static void toByteArray() {

        FileInputStream fis = null;
        File file = new File("/Users/wyann/test.txt");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            fis = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int length;
            while ((length = fis.read(buf)) != -1) {
                //
                outputStream.write(buf, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(new String(outputStream.toByteArray(), StandardCharsets.UTF_8));
    }
}
