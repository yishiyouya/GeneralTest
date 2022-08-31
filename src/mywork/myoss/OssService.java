package mywork.myoss;

/**
 * @author 15210
 */
public interface OssService {

    String upload(String objectName, String filePath);
    void downloadFile(String basePath, String fileName);
    boolean delete(String relativePath);

}
