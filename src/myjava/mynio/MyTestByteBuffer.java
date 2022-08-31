package myjava.mynio;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class MyTestByteBuffer {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        byte[] bytes =
                getByteFromString("43 00 00 00 4e 78 9c 72 61 60 60 f0 72 03 12 8e 8c 8c 73 d5 8b 59 0c f5 2c 19 66 42 e9 59 ea c5 ec 46 06 86 46 7a 86 0c 2b 59 8a b9 b3 52 13 f3 72 e3 73 32 0b 73 19 b6 ab 17 f3 25 a7 14 5b 1a 38 80 04 75 cb 72 19 16 a9 07 03 0d 61 00 00 00 00 ff ff");
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        printByteBuffer(buffer);
        
    }

    public static void printByteBuffer(ByteBuffer header) {
        Charset charset = Charset.forName("UTF-8");
        System.out.println(header.array().length);
        System.out.println(Arrays.toString(header.array()));
        String headStr = "";
        if (null == header) {
            try {
                headStr = charset.newDecoder().decode(header).toString();
            } catch (CharacterCodingException e1) {
            }
        }
        System.out.println(headStr);
        
        
    }
    
    public static byte[] getByteFromString(String value) {
        String[] chunks = value.split(" ");
        byte[] result = new byte[chunks.length];

        for (int i = 0; i < chunks.length; i++) {
            result[i] = (byte) Integer.parseInt(chunks[i], 16);
        }

        return result;
    }
    
}
