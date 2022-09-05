package mywork.mytesthttpclient;

import org.apache.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;

public class MyTestHttp {

    public static void main(String[] args) {

    }


    //public void get() {
    //    RestTemplate restTemplate = new RestTemplate();
    //    restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
    //    String uri = "https://www.baidu.com";
    //    HttpHeaders headers = new HttpHeaders();
    //    headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
    //    HttpEntity<String> entity = new HttpEntity<String>(headers);
    //    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
    //    System.out.println(response);
    //    System.out.println(response.getBody());
    //
    //    return;
    //}
    //
    //public static String post(String uri) {
    //    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    //    params.add("user", "你好");
    //
    //    // 以表单的方式提交
    //    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    //    //将请求头部和参数合成一个请求
    //    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
    //
    //    ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
    //
    //    return response.getBody();
    //}
}
