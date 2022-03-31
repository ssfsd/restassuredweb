package com.example.mysqlandjpa.assured;

import com.alibaba.fastjson.JSONObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
@Component
public class AssuredUtils {
private static AssuredUtils assuredUtils=new AssuredUtils();
public AssuredUtils(){

}
public static AssuredUtils getInstance(){
return assuredUtils;
}
    private  RequestSpecification requestSpecification;

    public RequestSpecification setUrlAndHeaders(String url, String headerList, int port){
        Map<String,String> header= JSONObject.parseObject(headerList,Map.class);
        baseURI=url;
        RestAssured.port=port;
        RequestSpecBuilder builder=new RequestSpecBuilder();
        builder.addHeaders(header);
        requestSpecification=builder.build();
        return requestSpecification;
    }
}
