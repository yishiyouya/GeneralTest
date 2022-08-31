package myjava.myio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MyFileStream {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testStreamSync(false);
        testStreamSync(true);
    }
    
    public static void testStreamSync(boolean outFlag){
        String content = "20190507-05:36:14.897: 8=FIX.4.29=38135=D34=849=SATAT_zm50=1988952=20190507-05:36:14.89756=Scale_Strategyzm57=Scale_Strategyzm116=19889143=AGCY1=00000020793611=D05070105000915=CNY38=200040=154=155=60000059=060=20190507-05:36:1466=00000B100=SS126=20190507-06:36:13167=CS526=000000207936_00000B_10500086061=EC6062=20190507-05:34:006063=20190507-06:36:136064=336065=522204=N22206=N25001=BJ110=099";
        String pathname = "E://test"+outFlag+".log";
        long start = System.currentTimeMillis();
        FileOutputStream fio = null;
        FileChannel fiChannel = null;
        ByteBuffer buf = ByteBuffer.allocate(5*1024);
        try {
            fio = new FileOutputStream(new File(pathname));
            fiChannel = fio.getChannel();
            for (int i = 0; i < 1<<11; i++) {
                // 将数据装入缓冲区
                buf.put(content.getBytes());
                // 反转缓冲区(limit设置为position,position设置为0,mark设置为-1)
                buf.flip();
                // 将缓冲区中的数据写入到通道
                fiChannel.write(buf);
                // 写完将缓冲区还原(position设置为0,limit设置为capacity,mark设置为-1)
                buf.clear();
            }
            if (outFlag) {
                fiChannel.force(true);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } /*finally{
            if(fio != null){
                try {
                    fio.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }*/
        long cost = System.currentTimeMillis() - start;
        System.out.println(outFlag +" "+ cost);
    }

}
