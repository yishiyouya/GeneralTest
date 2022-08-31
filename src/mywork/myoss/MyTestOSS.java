package mywork.myoss;

import com.aliyun.oss.*;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;

/**
 * 测试 阿里云存储 OSS
 *
 */
public class MyTestOSS {

    public static void main(String[] args){
        testOSSDownload();
    }

    public static void testOSSDownload(){
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如 badfox/testOSS.txt。
        String objectName = "badfox/abc/testOSS.txt";
        // 填写本地文件的完整路径，例如D://Download//bonc//testOSS.txt
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        String fileName = "D:\\Download\\testOSS_Down1.txt";

        OssServiceImpl ossImpl = new OssServiceImpl();
        ossImpl.upload(objectName, fileName);
    }



    public static void testOSSUpload(){
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如 badfox/testOSS.txt。
        String objectName = "badfox/testOSS.txt";
        // 填写本地文件的完整路径，例如D://Download//bonc//testOSS.txt
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        String filePath = "D://Download//bonc//testOSS.txt";

        OssServiceImpl ossImpl = new OssServiceImpl();
        ossImpl.upload(objectName, filePath);
    }

}
