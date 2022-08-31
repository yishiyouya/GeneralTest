package myjava.mylangs.teststring;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class TestSteamTokernizer {

    public static void main(String[] args) {
        replaceWhiteSpace();
    }
    
    //去掉0-9，a-z，A-Z以外的所有字符
    private static void replaceWhiteSpace() {
        String src = "%^&*()0943943aabc";
        StreamTokenizer st = new StreamTokenizer(new StringReader(src));
        st.whitespaceChars(0, '0' - 1);
        st.whitespaceChars('9' + 1, 'A' - 1);
        st.whitespaceChars('Z' + 1, 'a' - 1);

        StringBuffer buf = new StringBuffer();
        try {
            int ttype = st.nextToken();
            while (ttype != StreamTokenizer.TT_EOF) {
                if (ttype == StreamTokenizer.TT_WORD) {
                    buf.append(st.sval);
                }
                ttype = st.nextToken();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(buf.toString());
    }
    
}
