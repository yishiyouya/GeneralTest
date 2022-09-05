package mywork.mylombok;

import lombok.Cleanup;
import lombok.val;

import java.io.*;
import java.util.ArrayList;

/**
 * ≤‚ ‘ lombok
 */
public class MyTestLombok {

    public static void main(String[] args) {
        testVal();
    }

    public static void testVal() {
        val localList = new ArrayList<String>();
        localList.add("hhh");
        System.out.println(localList.get(0));

    }

    public static void testCleanIo(String[] args) throws Exception {
        @Cleanup InputStream in = new FileInputStream(args[0]);
        @Cleanup OutputStream out = new FileOutputStream(args[1]);
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) {
                break;
            }
            out.write(b, 0, r);
        }
    }

}
