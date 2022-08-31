package myjava.mybyte;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TestByteSerai {

    public static void main(String[] args) throws Exception {

        String key = "listNews";

        // Byte 写入 Redis
        List<News> news = new ArrayList<News>();
        news.add(new News("title1","body1"));
        news.add(new News("title2","body2"));
        news.add(new News("title3","body3"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(news);

        //关闭流
        oos.close();

        byte[] newsByte = baos.toByteArray();
        // 读取 Byte格式 存入的数据
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(newsByte);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        List<News> o = (List<News>) objectInputStream.readObject();

        System.out.println(o);

    }
    
}
